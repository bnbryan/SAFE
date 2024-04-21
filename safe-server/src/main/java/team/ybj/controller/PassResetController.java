package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team.ybj.exception.PassResetException;
import team.ybj.exception.ServiceException;
import team.ybj.exception.UsernameDuplicatedException;
import team.ybj.pojo.ResponseResult;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.PasswordService;


@RestController
public class PassResetController {

    @Autowired
    PasswordService PasswordService;

    @PostMapping("pass_reset")
    @ResponseBody
    public ResponseResult PassReset(@RequestBody YbjCustomer customer) {
        ResponseResult responseResult;
        try {
            responseResult = PasswordService.PassReset(customer);
        }catch (UsernameDuplicatedException e){
            responseResult = new ResponseResult(400, "No Account Found", 1);
        }catch (ServiceException e) {
            responseResult = new ResponseResult(400, "Wrong security question or answer", 1);
        }catch (PassResetException e) {
            responseResult = new ResponseResult(400, "Something went wrong when trying to reset password", 1);
        }
        return responseResult;
    }
}
