package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.ResponseResult;
import team.ybj.exception.*;
import team.ybj.pojo.*;
import team.ybj.service.WithdrawService;


@RestController
@RequestMapping("transactions")
public class WithdrawController {

    @Autowired
    WithdrawService withdrawService;

    @PostMapping("withdraw")
    @ResponseBody
    public ResponseResult WithDraw(@RequestBody YbjChecking checking) {
        ResponseResult responseResult;
        try {
            responseResult = withdrawService.Withdraw(checking);
        }catch (LackBalanceException e){
            responseResult = new ResponseResult(400, "Balance not enough", 0);
        }catch (ServiceException e) {
            responseResult = new ResponseResult(400, "Something went wrong when withdrawing", 0);
        }
        return responseResult;
    }
}
