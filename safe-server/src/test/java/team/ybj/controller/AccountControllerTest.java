package team.ybj.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import team.ybj.mappers.*;
import team.ybj.pojo.YbjAccount;
import team.ybj.service.AccountService;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountMapper accountMapper;

    @MockBean
    private AddressMapper addressMapper;

    @MockBean
    private CheckingMapper checkingMapper;
    @MockBean
    private CustomerMapper customerMapper;
    @MockBean
    private CompanyMapper companyMapper;
    @MockBean
    private CusAddLinkMapper cusAddLinkMapper;
    @MockBean
    private InsuranceMapper insuranceMapper;
    @MockBean
    private SavingMapper savingMapper;
    @MockBean
    private LoanMapper loanMapper;
    @MockBean
    private UniversityMapper universityMapper;

    @MockBean
    private YbjAccount  expectedAccount;

    @BeforeEach
    void setUp() throws Exception {
        long timestamp = 1672549200000L;
        Date aDate = new Date(timestamp);
        expectedAccount = new YbjAccount(1L,"John Checking",aDate,'C',1L,1L);
        when(accountService.findAccountById(any(Long.class))).thenReturn(expectedAccount);
    }

    @Test
    @WithMockUser
    public void getAccountTest() throws Exception {
        String expectedJson = "{\"anum\":1,\"aname\":\"John Checking\",\"adate\":\"2023-01-01T05:00:00.000+00:00\",\"atype\":\"C\",\"cid\":1,\"adid\":1}";
        mockMvc.perform(MockMvcRequestBuilders.get("/account/getAccount").param("id","1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));

    }
}
