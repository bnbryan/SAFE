package team.ybj.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.ybj.exception.PassResetException;
import team.ybj.exception.ServiceException;
import team.ybj.exception.UsernameDuplicatedException;
import team.ybj.mappers.CustomerMapper;
import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.PasswordService;


@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private CustomerMapper customerMapper;

    @Cacheable(value = "customers", key = "#email")
    public YbjCustomer getValidCustomerByEmail(String email) {
        return customerMapper.getValidCustomerByEmail(email);
    }

    @Cacheable(value = "customers", key = "#email")
    public YbjCustomer getCustomerByEmail(String email) {
        return customerMapper.getCustomerByEmail(email);
    }

    @CachePut(value = "customers", key = "#cid")
    public void updatePasswordByCid(Long cid, String password) {
        customerMapper.updatePasswordByCid(cid, password);
    }

    @CacheEvict(value = "customers", key = "#cid")
    public void evictCache(Long cid) {
        // This method is used to explicitly evict cache for a specific email.
    }

    @Override
    public ResponseResult PassReset(YbjCustomer customer) {
        String email = customer.getCemail();
        YbjCustomer checkValid = getValidCustomerByEmail(email);
        if(checkValid == null) {
            throw new UsernameDuplicatedException("No Account Found");
        }
        String SA = customer.getSecurityAnswer();
        String SQ = customer.getSecurityQuestion();
        String NewPass = customer.getCpassword();
        YbjCustomer ExpCustomer = getCustomerByEmail(email);
        if(!SA.equals(ExpCustomer.getSecurityAnswer()) || !SQ.equals(ExpCustomer.getSecurityQuestion())) {
            throw new ServiceException("Invalid Security Question or Answer");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(NewPass);
        try{
            updatePasswordByCid(ExpCustomer.getCid(), encodePassword);
            evictCache(ExpCustomer.getCid()); // Evict cache after password update
        } catch(Exception e) {
            throw new PassResetException("Something went wrong when trying to reset password");
        }
        return new ResponseResult(200, "Password reset success", 1);
    }

}
