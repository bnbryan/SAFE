package team.ybj.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.dto.UserGetAppsResponse;
import team.ybj.mappers.AccountAppMapper;
import team.ybj.pojo.AccountApp;

import java.util.List;

@SpringBootTest
public class ApplicationServiceTest {
    @Resource
    private ApplicationService applicationService;
    @Resource
    private AccountAppMapper accountAppMapper;

    @Test
    @Transactional
    public void testApplyForAccounts() {
        AccountApp accountApp = new AccountApp(8L, 'C', 10000.00, "student");
        Long actual = applicationService.applyForAccount(accountApp);
        Assertions.assertNotNull(actual);

        accountAppMapper.deleteAccountAppById(actual);
    }

    @Test
    public void testGetUserApps() {
        List<UserGetAppsResponse> actual = applicationService.getUserApps(5L);
        UserGetAppsResponse expectedUserApp = new UserGetAppsResponse(1L, 'C', null);
        List<UserGetAppsResponse> expected = List.of(expectedUserApp);
        Assertions.assertEquals(expected, actual);
    }
}
