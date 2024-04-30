package team.ybj.controller;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.DepositRequest;
import team.ybj.dto.ResponseResult;
import team.ybj.dto.TransferRequest;
import team.ybj.exception.LackBalanceException;
import team.ybj.exception.ServiceException;
import team.ybj.pojo.YbjChecking;
import team.ybj.service.DepositService;
import team.ybj.service.RecordService;
import team.ybj.service.TransferService;
import team.ybj.service.WithdrawService;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Resource
    private TransferService transferService;
    @Resource
    private DepositService depositService;
    @Resource
    private RecordService recordService;
    @Resource
    private WithdrawService withdrawService;

    @PostMapping("transfer")
    @ResponseBody
    @Transactional
    public ResponseEntity<ResponseResult<Integer>> transfer(@RequestBody TransferRequest transferRequest) {
        int transfer = transferService.transfer(transferRequest);
        if (transfer > 0) {
            ResponseResult<Integer> successResult = new ResponseResult<>(200, "transfer success", transfer);
            recordService.AddRe(30L, transferRequest.getToAccountNum(),
                    String.valueOf(transferRequest.getFromAccountType()),transferRequest.getAmount());
            return ResponseEntity.ok(successResult);
        } else {
            ResponseResult<Integer> errorResult = new ResponseResult<>(422, "transfer fail", transfer);
            return new ResponseEntity<>(errorResult, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    };

    @PostMapping("deposit")
    @ResponseBody
    @Transactional
    public ResponseResult<Double> deposit(@RequestBody DepositRequest depositRequest) {
        Double currentBalance = depositService.deposit(depositRequest);
        recordService.AddRe(null, depositRequest.getAccountNum(),
                null,depositRequest.getAmount());
        return new ResponseResult<>(200, "deposit success", currentBalance);
    }

    @PostMapping("withdraw")
    @ResponseBody
    @Transactional
    public ResponseResult WithDraw(@RequestBody YbjChecking checking) {
        ResponseResult responseResult;
        try {
            responseResult = withdrawService.Withdraw(checking);
            recordService.AddRe(checking.getAnum(), null,
                    String.valueOf(checking.getAtype()),checking.getAbalance());
        }catch (LackBalanceException e){
            responseResult = new ResponseResult(400, "Balance not enough", 0);
        }catch (ServiceException e) {
            responseResult = new ResponseResult(400, "Something went wrong when withdrawing", 0);
        }
        return responseResult;
    }
}
