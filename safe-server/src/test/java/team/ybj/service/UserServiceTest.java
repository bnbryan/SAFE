package team.ybj.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.ybj.dto.CustomerBasic;
import team.ybj.pojo.YbjAddress;
import team.ybj.pojo.YbjCustomer;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindCustomerByEmail() {
        CustomerBasic actual = userService.findCustomerByEmail("123@gmail.com");
        YbjAddress address = new YbjAddress(1L, "123 Main St", "Downtown", "CA", null, "90001");
        CustomerBasic expected = new CustomerBasic(1L, "Smith", "John", "123@gmail.com", address);
        Assertions.assertEquals(expected, actual);
    }
}
