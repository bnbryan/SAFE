package team.ybj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import team.ybj.pojo.LoginUser;
import team.ybj.pojo.ResponseResult;
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

    @Override
    public ResponseResult login(YbjCustomer customer) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customer.getCemail(), customer.getCpassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("login failed");
        }
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String cemail = loginUser.getCustomer().getCemail();
        String jwt = JwtUtil.generateToken(cemail);
        Map<String, String> jwtMap = new HashMap<>();
        jwtMap.put("token", jwt);
        return new ResponseResult(200, "login success", jwtMap);
    }
}
