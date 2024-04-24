package team.ybj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.ybj.pojo.YbjAccount;
import team.ybj.service.impl.AccountServiceImpl;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private ObjectMapper objectMapper;

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    public void testFindAccountById() throws JsonProcessingException {
        YbjAccount account = accountService.findAccountById(1L);
        String expectedJson = "{\"anum\":1,\"aname\":\"John Checking\",\"adate\":\"2023-01-01T05:00:00.000+00:00\",\"atype\":\"C\",\"cid\":1,\"adid\":1}";

        String actualJson = objectMapper.writeValueAsString(account);
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testFindAllAccounts() throws JsonProcessingException {
        List<YbjAccount> accounts = accountService.findAllAccounts(1L);
        String expectedJson = "[{\"anum\":1,\"aname\":\"John Checking\",\"adate\":\"2023-01-01T05:00:00.000+00:00\",\"atype\":\"C\",\"cid\":1,\"adid\":1}," +
                "{\"anum\":15,\"aname\":\"John Savings\",\"adate\":\"2024-01-01T05:00:00.000+00:00\",\"atype\":\"S\",\"cid\":1,\"adid\":1}]";

        String actualJson = objectMapper.writeValueAsString(accounts);
        Assertions.assertEquals(expectedJson, actualJson);

    }
}
