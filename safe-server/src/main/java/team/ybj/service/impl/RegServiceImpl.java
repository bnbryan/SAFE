package team.ybj.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.ybj.mappers.CustomerMapper;
import team.ybj.pojo.ResponseResult;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.RegService;

@Service
public class RegServiceImpl implements RegService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public ResponseResult reg(YbjCustomer customer) {
        String email = customer.getCemail();
        YbjCustomer checkEmail = customerMapper.getCustomerByEmail(email);
        if(checkEmail != null) {
            throw new RuntimeException("Email already in use");
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
