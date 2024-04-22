package team.ybj.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.dto.DepositRequest;
import team.ybj.mappers.CheckingMapper;
import team.ybj.mappers.SavingMapper;
import team.ybj.pojo.YbjChecking;
import team.ybj.pojo.YbjSaving;

@SpringBootTest
public class DepositServiceTest {
    @Resource
    private DepositService depositService;
    @Resource
    private CheckingMapper checkingMapper;
    @Resource
    private SavingMapper savingMapper;

    @Test
    @Transactional
    public void depositCheckingTest() {
        DepositRequest request = new DepositRequest(1L, 100.00);
        YbjChecking account = checkingMapper.getCheckingByAnum(1L);
        Double amount = depositService.deposit(request);
        Assertions.assertEquals(account.getAbalance() + 100.00, amount);
        //restore data
        checkingMapper.updateAbalanceByAnum(1L, account.getAbalance());
    }

    @Test
    public void depositSavingTest() {
        DepositRequest request = new DepositRequest(2L, 100.00);
        YbjSaving account = savingMapper.getSavingByAnum(2L);
        Double amount = depositService.deposit(request);
        Assertions.assertEquals(account.getSbalance() + 100.00, amount);
        //restore data
        savingMapper.updateSbalanceByAnum(2L, account.getSbalance());
    }
}
