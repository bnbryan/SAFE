package team.ybj.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import team.ybj.pojo.YbjAccount;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    public void testFindAccountById() throws JsonProcessingException {
        YbjAccount account = accountService.findAccountById(1);
        String expectedJson = "{\"anum\":1,\"aname\":\"John Checking\",\"adate\":1672549200000,\"atype\":\"C\",\"cid\":1,\"adid\":1}";

        ObjectMapper mapper = new ObjectMapper();
        String actualJson = mapper.writeValueAsString(account);
        Assertions.assertEquals(expectedJson, actualJson);
    }
}
