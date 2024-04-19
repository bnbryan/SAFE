package team.ybj.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.ybj.pojo.YbjCustomer;
import team.ybj.mappers.CustomerMapper;
import java.util.Random;
import team.ybj.pojo.ResponseResult;

@SpringBootTest
public class RegLoginTest {
    @Autowired
    RegService regService;
    @Autowired
    LoginService loginService;
    @Test
    public void Reg() {

        try{
            YbjCustomer customer = new YbjCustomer();
            customer.setCemail("test@test.com");
            customer.setCpassword("123456");
            customer.setCvalid("1");
            customer.setCfname("test3");
            customer.setClname("sda");
            customer.setSecurityAnswer("asd");
            customer.setSecurityQuestion("test ques");
            regService.reg(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void login() {
        YbjCustomer customer = new YbjCustomer();
        customer.setCemail("156@gmail.com");
        customer.setCpassword("12345");
        try {
           ResponseResult result = loginService.login(customer);
            System.out.println(result.getMsg());
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}