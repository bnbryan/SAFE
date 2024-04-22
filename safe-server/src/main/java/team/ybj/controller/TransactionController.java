package team.ybj.controller;

import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.DepositRequest;
import team.ybj.dto.ResponseResult;
import team.ybj.dto.TransferRequest;
import team.ybj.service.DepositService;
import team.ybj.service.TransferService;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Resource
    private TransferService transferService;
    @Resource
    private DepositService depositService;

    @PostMapping("transfer")
    @ResponseBody
    public ResponseEntity<ResponseResult<Integer>> transfer(@RequestBody TransferRequest transferRequest) {
        int transfer = transferService.transfer(transferRequest);
        if (transfer > 0) {
            ResponseResult<Integer> successResult = new ResponseResult<>(200, "transfer success", transfer);
            return ResponseEntity.ok(successResult);
        } else {
            ResponseResult<Integer> errorResult = new ResponseResult<>(422, "transfer fail", transfer);
            return new ResponseEntity<>(errorResult, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    };

    @PostMapping("deposit")
    @ResponseBody
    public ResponseResult<Double> deposit(@RequestBody DepositRequest depositRequest) {
        Double currentBalance = depositService.deposit(depositRequest);
        return new ResponseResult<>(200, "deposit success", currentBalance);
    }

}
