package team.ybj.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.mappers.AccountMapper;
import team.ybj.mappers.CheckingMapper;
import team.ybj.pojo.YbjAccount;
import team.ybj.pojo.YbjChecking;

import java.util.Date;
import java.util.Random;

@SpringBootTest
public class CheckingMapperTest {
    @Autowired
    CheckingMapper checkingMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AccountMapper accountMapper;

    @Test
    public void getCheckingByAnumTest() {
        YbjChecking actual = checkingMapper.getCheckingByAnum(1L);
        YbjChecking expected = new YbjChecking(1L, 25.00, 'C', 2300.00, 'Y');

        Assertions.assertEquals(actual, expected);
    }

    @Test
    @Transactional
    public void insertCheckingTest() {
        long ts1 = 1709701200;
        Random random = new Random();
        Long anum = random.nextLong();
        YbjAccount account = new YbjAccount(anum, "Michael Checking", new Date(ts1 * 1000), 'C', 3L, 3L);
        int success = accountMapper.insertAccount(account);
        Assertions.assertEquals(1, success);

        YbjChecking checking = new YbjChecking(anum, 25.00, 'C', 2300.00, 'Y');
        int checkingSuccess = checkingMapper.insertChecking(checking);
        Assertions.assertEquals(1, checkingSuccess);

        // restore data
        checkingMapper.deleteCheckingByAnum(anum);
        accountMapper.deleteAccountByAnum(anum);
    }

    @Test
    public void updateCchargeByAnumTest() {
        int success = checkingMapper.updateCchargeByAnum(1L,0.00);
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = checkingMapper.updateCchargeByAnum(1L, 25.00);
        } while (success2 != 1);
    }

    @Test
    public void updateAbalanceByAnumTest() {
        int success = checkingMapper.updateAbalanceByAnum(1L,100.00);
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = checkingMapper.updateAbalanceByAnum(1L, 2300.00);
        } while (success2 != 1);

    }

    @Test
    public void updateCvalidByAnumTest() {
        int success = checkingMapper.updateCvalidByAnum(1L,'P');
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = checkingMapper.updateCvalidByAnum(1L, 'Y');
        } while (success2 != 1);

    }
}
