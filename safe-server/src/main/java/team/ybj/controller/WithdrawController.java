package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.exception.*;
import team.ybj.mappers.CheckingMapper;
import team.ybj.mappers.SavingMapper;
import team.ybj.pojo.*;
import team.ybj.service.PasswordService;
import team.ybj.service.WithdrawService;


@RestController
public class WithdrawController {

    @Autowired
    private SavingMapper savingService;
    @Autowired
    private CheckingMapper checkingMapper;
    @Autowired
    WithdrawService withdrawService;

    @PostMapping("/transactions/withdraw")
    @ResponseBody
    public ResponseResult WithDraw(@RequestBody YbjChecking checking) {
        ResponseResult responseResult;
        try {
            responseResult = withdrawService.Withdraw(checking);
        }catch (LackBalanceException e){
            responseResult = new ResponseResult(400, "Balance not enough", 1);
        }catch (ServiceException e) {
            responseResult = new ResponseResult(400, "Something went wrong when withdrawing", 1);
        }
        return responseResult;
    }
}
