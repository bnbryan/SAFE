package team.ybj.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.mappers.AccountAppMapper;
import team.ybj.pojo.AccountApp;
import team.ybj.pojo.YbjAccount;

import java.util.Date;
import java.util.Random;

@SpringBootTest
public class AccountAppMapperTest {
    @Resource
    private AccountAppMapper mapper;

    @Test
    @Transactional
    public void insertAccountAppTest() {
        AccountApp accountApp = new AccountApp(8L, 'C', 10000.00, "student");
        int success = mapper.insertAccountApp(accountApp);
        Long appId = accountApp.getAppId();
        Assertions.assertEquals(1, success);
        // restore data
        mapper.deleteAccountAppById(appId);
    }
}
