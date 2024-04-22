package team.ybj.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.dto.TransferRequest;

@SpringBootTest
public class TransferServiceTest {
    @Resource
    TransferService transferService;

    @Test
    @Transactional
    public void testTransferBetweenCheckingAndChecking() throws Exception {
        TransferRequest transferRequest = new TransferRequest(1L, 'C', 18L, 100.00);
        int transfer = transferService.transfer(transferRequest);
        // restore data
        TransferRequest reverseRequest = new TransferRequest(18L, 'C', 1L, 100.00);
        transferService.transfer(reverseRequest);
        Assertions.assertEquals(2, transfer);
    }

    @Test
    @Transactional
    public void testTransferBetweenCheckingAndSaving() throws Exception {
        TransferRequest transferRequest = new TransferRequest(1L, 'C', 2L, 100.00);
        int transfer = transferService.transfer(transferRequest);
        // restore data and test transfer from saving to checking
        TransferRequest reverseRequest = new TransferRequest(2L, 'S', 1L, 100.00);
        transfer += transferService.transfer(reverseRequest);
        Assertions.assertEquals(4, transfer);
    }

    @Test
    @Transactional
    public void testTransferFromChecking2Checking() throws Exception {
        TransferRequest transferRequest = new TransferRequest(2L, 'S', 5L, 100.00);
        int transfer = transferService.transfer(transferRequest);
        // restore data
        TransferRequest reverseRequest = new TransferRequest(5L, 'S', 2L, 100.00);
        transferService.transfer(reverseRequest);
        Assertions.assertEquals(2, transfer);
    }



}
