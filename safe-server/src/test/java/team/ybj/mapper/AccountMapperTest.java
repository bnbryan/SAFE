package team.ybj.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.ybj.mappers.AccountMapper;
import team.ybj.pojo.YbjAccount;

import java.util.*;

@SpringBootTest
public class AccountMapperTest {

    @Autowired
    AccountMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAccountByIdTest() throws JsonProcessingException {
        YbjAccount account =  mapper.getAccountById(1);
        String expectedJson = "{\"anum\":1,\"aname\":\"John Checking\",\"adate\":\"2023-01-01T05:00:00.000+00:00\",\"atype\":\"C\",\"cid\":1,\"adid\":1}";

        String actualJson = objectMapper.writeValueAsString(account);
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void getAccountByCidTest() throws JsonProcessingException {
        List<YbjAccount> actual = mapper.getAccountsByCid(2);
        long ts1 = 1709701200;
        long ts2 = 1705726800;
        long ts3 = 1675227600;

        YbjAccount ac1 = new YbjAccount(22, "Emily Checking", new Date(ts1 * 1000), 'C', 2, 2);
        YbjAccount ac2 = new YbjAccount(16, "Emily Loan", new Date(ts2 * 1000), 'L', 2, 2);
        YbjAccount ac3 = new YbjAccount(2, "Emily Savings", new Date(ts3 * 1000), 'S', 2, 2);

        List<YbjAccount> expected = Arrays.asList(ac1, ac2, ac3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void insertAccountTest() {
        long ts1 = 1709701200;
        Random random = new Random();
        int anum = random.nextInt();
        YbjAccount account = new YbjAccount(anum, "John loan", new Date(ts1 * 1000), 'L', 1, 1);
        int success = mapper.insertAccount(account);
        Assertions.assertEquals(1, success);
        // restore data
        mapper.deleteAccountByAnum(anum);
    }

    @Test
    public void updateAnameByAnumTest() {
        int success = mapper.updateAnameByAnum(1, "Checking John");
        Assertions.assertEquals(1, success);
        int success2;
        // restore data
        do{
            success2 = mapper.updateAnameByAnum(1, "John Checking");
        } while (success2 != 1);
    }

    @Test
    public void updateAdidByAnumTest() {
        int success = mapper.updateAdidByAnum(1, 3);
        Assertions.assertEquals(1, success);
        int success2;
        // restore data
        do{
            success2 = mapper.updateAdidByAnum(1, 1);
        } while (success2 != 1);
    }

}
