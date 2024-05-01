package team.ybj.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.mappers.LoanMapper;
import team.ybj.pojo.YbjAccount;
import team.ybj.pojo.YbjLoan;
import team.ybj.mappers.AccountMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


@SpringBootTest
public class LoanMapperTest {

    @Autowired
    LoanMapper loanMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    AccountMapper accountMapper;

    /*@Test
    public void getLoanByAnumTest() throws JsonProcessingException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 1, 0, 0, 0);  // 注意月份从0开始，所以6月是Calendar.JUNE
        Date date = calendar.getTime();
        YbjLoan actual = loanMapper.getLoanByAnum(3L);
        YbjLoan expected = new YbjLoan(3L, 4.5, 10000.00, (short) 60, 200.00, "STU",
                null, null, null,  "202300001", 'G', date, 1L, 'L', 'P');

        Assertions.assertEquals(expected, actual);
    }*/

    @Test
    @Transactional
    public void insertLoanTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 1, 0, 0, 0);  // 注意月份从0开始，所以6月是Calendar.JUNE
        Date date = calendar.getTime();
        long ts1 = 1709701200;
        YbjAccount account = new YbjAccount("John Loan", new Date(ts1 * 1000), 'L', 1L, 3L);
        int success = accountMapper.insertAccount(account);
        Assertions.assertEquals(1, success);

        YbjLoan loan = new YbjLoan(account.getAnum(), 5.7, 3000.00, (short) 50, 100.00, "STU", null, null, null,  "230230013", 'U', date, 3L, 'L', 'Y');
        int success2 = loanMapper.insertLoan(loan);
        Assertions.assertEquals(1, success2);
        // restore data
        loanMapper.deleteLoanByAnum(loan.getAnum());
        accountMapper.deleteAccountByAnum(account.getAnum());
    }

    @Test
    public void updateLrateByAnumTest() {
        int success = loanMapper.updateLrateByAnum(3L,6.0);
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = loanMapper.updateLrateByAnum(3L, 4.5);
        } while (success2 != 1);
    }

    @Test
    public void updateLamountByAnumTest() {
        int success = loanMapper.updateLamountByAnum(3L,120000.0);
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = loanMapper.updateLamountByAnum(3L, 10000.00);
        } while (success2 != 1);
    }

    @Test
    public void updateLvalidByAnumTest() {
        int success = loanMapper.updateLvalidByAnum(3L,'Y');
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = loanMapper.updateLvalidByAnum(3L, 'P');
        } while (success2 != 1);
    }

    @Test
    public void updateLpaymentByAnumTest() {
        int success = loanMapper.updateLpaymentByAnum(3L,23454.0);
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = loanMapper.updateLpaymentByAnum(3L, 200.00);
        } while (success2 != 1);
    }

    @Test
    public void updateLmonthsByAnumTest() {
        int success = loanMapper.updateLmonthsByAnum(3L, (short) 10);
        Assertions.assertEquals(1, success);

        // restore data
        int success2;
        do{
            success2 = loanMapper.updateLmonthsByAnum(3L, (short) 60);
        } while (success2 != 1);
    }


}
