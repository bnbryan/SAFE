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
        YbjAccount account =  mapper.getAccountById(1L);
        String expectedJson = "{\"anum\":1,\"aname\":\"John Checking\",\"adate\":\"2023-01-01T05:00:00.000+00:00\",\"atype\":\"C\",\"cid\":1,\"adid\":1}";

        String actualJson = objectMapper.writeValueAsString(account);
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void getAccountByCidTest() throws JsonProcessingException {
        List<YbjAccount> actual = mapper.getAccountsByCid(2L);
        long ts1 = 1709701200;
        long ts2 = 1705726800;
        long ts3 = 1675227600;

        YbjAccount ac1 = new YbjAccount(22L, "Emily Checking", new Date(ts1 * 1000), 'C', 2L, 2L);
        YbjAccount ac2 = new YbjAccount(16L, "Emily Loan", new Date(ts2 * 1000), 'L', 2L, 2L);
        YbjAccount ac3 = new YbjAccount(2L, "Emily Savings", new Date(ts3 * 1000), 'S', 2L, 2L);

        List<YbjAccount> expected = Arrays.asList(ac1, ac2, ac3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getAccountByEmailTest() throws JsonProcessingException {
        List<YbjAccount> actual = mapper.getAccountsByEmail("asde@gmai.com");
        long ts1 = 1709701200;
        long ts2 = 1705726800;
        long ts3 = 1675227600;

        YbjAccount ac1 = new YbjAccount(22L, "Emily Checking", new Date(ts1 * 1000), 'C', 2L, 2L);
        YbjAccount ac2 = new YbjAccount(16L, "Emily Loan", new Date(ts2 * 1000), 'L', 2L, 2L);
        YbjAccount ac3 = new YbjAccount(2L, "Emily Savings", new Date(ts3 * 1000), 'S', 2L, 2L);

        List<YbjAccount> expected = Arrays.asList(ac1, ac2, ac3);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void insertAccountTest() {
        long ts1 = 1709701200;
        Random random = new Random();
//        long anum = random.nextLong();
        YbjAccount account = new YbjAccount("John loan", new Date(ts1 * 1000), 'L', 1L, 1L);
        int success = mapper.insertAccount(account);
        Assertions.assertEquals(1, success);
        // restore data
        mapper.deleteAccountByAnum(account.getAnum());
    }

    @Test
    public void updateAnameByAnumTest() {
        int success = mapper.updateAnameByAnum(1L, "Checking John");
        Assertions.assertEquals(1, success);
        int success2;
        // restore data
        do{
            success2 = mapper.updateAnameByAnum(1L, "John Checking");
        } while (success2 != 1);
    }

    @Test
    public void updateAdidByAnumTest() {
        int success = mapper.updateAdidByAnum(1L, 3L);
        Assertions.assertEquals(1, success);
        int success2;
        // restore data
        do{
            success2 = mapper.updateAdidByAnum(1L, 1L);
        } while (success2 != 1);
    }

}
