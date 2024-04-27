package team.ybj.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import team.ybj.mappers.AdminMapper;
import team.ybj.pojo.YbjAdmin;

@SpringBootTest
public class AdminMapperTest {
    @Resource
    private AdminMapper adminMapper;

    @Test
    public void testGetAdminByUsername() {
        YbjAdmin expected = new YbjAdmin(123L, "admin1","$2a$10$iBVWNCuhgmFbF/QXU3WDTOyUjq1JSbvrog/7PB2mvox0/9e.s9DLa");
        YbjAdmin actual = adminMapper.findAdminByUsername("admin1");

        Assertions.assertEquals(expected, actual);
    }
}
