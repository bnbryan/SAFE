package team.ybj.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.ybj.dto.ResponseResult;
import team.ybj.exception.*;
import team.ybj.mappers.CustomerMapper;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.DelAccService;

@Service
public class DelAccImpl implements DelAccService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public ResponseResult DelAcc(YbjCustomer customer) {
        String email = customer.getCemail();
        YbjCustomer customer1 = customerMapper.getCustomerByEmail(email);
        if (customer1 == null|| customer1.getCvalid().equals("0")) {
            throw new AccountTypeException("Account error");
        }
        try{
            customerMapper.updateValidByCid(customer1.getCid(), "0");
        } catch(Exception e){throw new ServiceException("Something went wrong when trying to delete customer");}
        return new ResponseResult(200, "Delete success", 1);
    }
}
