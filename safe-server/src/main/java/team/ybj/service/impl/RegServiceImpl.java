package team.ybj.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.ybj.exception.UsernameDuplicatedException;
import team.ybj.mappers.AdminMapper;
import team.ybj.mappers.CustomerMapper;
import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjAdmin;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.RegService;

@Service
public class RegServiceImpl implements RegService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ResponseResult reg(YbjCustomer customer) {
        String email = customer.getCemail();
        YbjAdmin checkAdmin = adminMapper.findAdminByUsername(email);
        YbjCustomer checkEmail = customerMapper.getCustomerByEmail(email);
        if(checkEmail != null || checkAdmin != null) {
            throw new UsernameDuplicatedException("Email already in use");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(customer.getCpassword());
        customer.setCpassword(encodePassword);
        try{
            customerMapper.insertCustomer(customer);
        } catch(Exception e){throw new RuntimeException("Something went wrong when trying to insert customer");}
        return new ResponseResult(200, "Register success", 1);
    }
}
