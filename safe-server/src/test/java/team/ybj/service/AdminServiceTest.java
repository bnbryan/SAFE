package team.ybj.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import team.ybj.pojo.YbjAdmin;
import team.ybj.utils.JwtUtil;

import java.util.Map;

@SpringBootTest
public class AdminServiceTest {

    @Resource
    private AdminService adminService;

    @Resource
    JwtUtil jwtUtil;

    @Test
    public void testAdminLogin() {
        YbjAdmin admin = new YbjAdmin(123L, "admin1", "123");
        Map<String, String> actual = adminService.login(admin);
        String token = actual.get("token");
        String actualUsername = jwtUtil.getSubjectFromToken(token);
        Assertions.assertEquals("admin1", actualUsername);
    }


}
