package team.ybj.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team.ybj.utils.JwtUtil;

@SpringBootTest
public class PasswordEncoderTest {
    @Autowired
    JwtUtil jwtUtil;
    @Test
    public void encode() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String s = encoder.encode("12345");
        System.out.println(s);

//        String token = jwtUtil.generateToken("123@gmail.com");
//        String subject = jwtUtil.getSubjectFromToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjNAZ21haWwuY29tIiwiaWF0IjoxNzEzNDE1MTc0LCJleHAiOjE3MTM0MTg3NzR9.TuDQY_nF3X6jw4PRCpLZc7nyWN7PabI4UT791W48ai8");
//        System.out.println(token);
//        System.out.println(subject);

    }
}
