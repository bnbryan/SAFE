package team.ybj.service.impl;

import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import team.ybj.exception.LoginException;
import team.ybj.mappers.AdminMapper;
import team.ybj.pojo.LoginAdmin;
import team.ybj.pojo.YbjAdmin;
import team.ybj.service.AdminService;
import team.ybj.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;
    @Resource
    AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> login(YbjAdmin admin) {
        isAdmin(admin.getUsername());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new LoginException("Username or password is incorrect/invalid");
        }
        LoginAdmin loginAdmin = (LoginAdmin) authenticate.getPrincipal();
        String id = loginAdmin.getAdmin().getUsername();
        String jwt = JwtUtil.generateToken(id);
        Map<String, String> jwtMap = new HashMap<>();
        jwtMap.put("token", jwt);
        return jwtMap;
    }

    public void isAdmin(String username) {
        if (adminMapper.findAdminByUsername(username) == null) {
            throw new LoginException("Admin account not exist");
        }
    }
}
