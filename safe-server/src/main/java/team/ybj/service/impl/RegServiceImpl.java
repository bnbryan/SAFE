package team.ybj.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.dto.RegRequest;
import team.ybj.exception.ServiceException;
import team.ybj.exception.UsernameDuplicatedException;
import team.ybj.mappers.*;
import team.ybj.dto.*;
import team.ybj.pojo.YbjAddress;
import team.ybj.pojo.YbjAdmin;
import team.ybj.pojo.YbjCusAddLink;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.RegService;

import java.util.Random;

@Service
public class RegServiceImpl implements RegService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CusAddLinkMapper cusAddLinkMapper;

    @Transactional
    @Override
    public ResponseResult reg(RegRequest regRequest) {
        String email = regRequest.getCemail();
        YbjAdmin checkAdmin = adminMapper.findAdminByUsername(email);
        YbjCustomer checkEmail = customerMapper.getCustomerByEmail(email);
        if(checkEmail != null || checkAdmin != null) {
            throw new UsernameDuplicatedException("Email already in use");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(regRequest.getCpassword());
        regRequest.setCpassword(encodePassword);
        YbjCustomer customer = new YbjCustomer(null, regRequest.getClname(), regRequest.getCfname(),
                regRequest.getCemail(), regRequest.getCpassword(), regRequest.getSecurityQuestion(),
                regRequest.getAnswer(), "1");

        YbjAddress address = new YbjAddress(null, regRequest.getAdstreet(), regRequest.getAdcity(),
                regRequest.getAdstate(), regRequest.getAdapt(), regRequest.getAdzip());
        try{
            customerMapper.insertCustomer(customer);
            Long cid = customerMapper.getLastInsertId();
            addressMapper.insertAddress(address);
            Long adid = customerMapper.getLastInsertId();
            YbjCusAddLink cusAddLink = new YbjCusAddLink(cid, adid);
            cusAddLinkMapper.insertCusAddLink(cusAddLink);
        } catch(Exception e){throw new ServiceException("Something went wrong when trying to insert customer");}
        return new ResponseResult(200, "Register success", 1);
    }
}
