package team.ybj.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import team.ybj.mappers.AccountMapper;
import team.ybj.pojo.YbjAccount;
import team.ybj.service.AccountService;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountMapper accountMapper;

    @MockBean
    private YbjAccount  expectedAccount;

    @BeforeEach
    void setUp() throws Exception {
        long timestamp = 1672549200000L;
        Date aDate = new Date(timestamp);
        expectedAccount = new YbjAccount(1,"John Checking",aDate,'C',1,1);
        when(accountService.findAccountById(any(Integer.class))).thenReturn(expectedAccount);
    }

    @Test
    public void getAccountTest() throws Exception {
        String expectedJson = "{\"anum\":1,\"aname\":\"John Checking\",\"adate\":\"2023-01-01T05:00:00.000+00:00\",\"atype\":\"C\",\"cid\":1,\"adid\":1}";
        mockMvc.perform(MockMvcRequestBuilders.get("/account/getAccount").param("id","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));

    }
}
