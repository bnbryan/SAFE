package team.ybj.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.ybj.mappers.AccountMapper;
import team.ybj.mappers.SavingMapper;
import team.ybj.pojo.YbjAccount;
import team.ybj.pojo.YbjSaving;

import java.util.Date;
import java.util.Random;

@SpringBootTest
public class SavingMapperTest {
    @Autowired
    SavingMapper savingMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AccountMapper accountMapper;

    @Test
    public void getSavingByAnumTest() {
        YbjSaving actual = savingMapper.getSavingByAnum(2L);
        YbjSaving expected = new YbjSaving(2L, 1.5, 'S', 52020.00, 'Y');

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void insertSavingTest() {
        long ts1 = 1709701200;
        Random random = new Random();
        Long anum = random.nextLong();
        YbjAccount account = new YbjAccount(anum, "Ma Saving", new Date(ts1 * 1000), 'S', 12L, 3L);
        int success = accountMapper.insertAccount(account);
        Assertions.assertEquals(1, success);

        YbjSaving saving = new YbjSaving(anum, 1.4, 'S', 2300.00, 'Y');
        int savingSuccess = savingMapper.insertSaving(saving);
        Assertions.assertEquals(1, savingSuccess);

        // restore data
        savingMapper.deleteSavingByAnum(anum);
        accountMapper.deleteAccountByAnum(anum);
    }

    @Test
    public void updateSrateByAnumTest() {
        int success = savingMapper.updateSrateByAnum(2L,0.2);
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = savingMapper.updateSrateByAnum(2L, 1.5);
        } while (success2 != 1);
    }

    @Test
    public void updateSbalanceByAnumTest() {
        int success = savingMapper.updateSbalanceByAnum(2L,100.00);
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = savingMapper.updateSbalanceByAnum(2L, 52020.00);
        } while (success2 != 1);

    }

    @Test
    public void updateSvalidByAnumTest() {
        int success = savingMapper.updateSvalidByAnum(2L,'N');
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = savingMapper.updateSvalidByAnum(2L, 'Y');
        } while (success2 != 1);

    }
}