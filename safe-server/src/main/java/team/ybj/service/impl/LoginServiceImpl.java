package team.ybj.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import team.ybj.exception.LoginException;
import team.ybj.mappers.CustomerMapper;
import team.ybj.pojo.LoginUser;
import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.LoginService;
import team.ybj.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public ResponseResult<Map<String,String>> login(YbjCustomer customer) {
        isCustomer(customer.getCemail());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customer.getCemail(), customer.getCpassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new LoginException("Username or password is incorrect/invalid");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String cemail = loginUser.getCustomer().getCemail();
        String jwt = JwtUtil.generateToken(cemail);
        Map<String, String> jwtMap = new HashMap<>();
        jwtMap.put("token", jwt);
        return new ResponseResult<>(200, "login success", jwtMap);
    }

    private void isCustomer(String cemail) {
        YbjCustomer customer = customerMapper.getCustomerByEmail(cemail);
        if (customer == null) {
            throw new LoginException("Account does not exist");
        }
    }
}
