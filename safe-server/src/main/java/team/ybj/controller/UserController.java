package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.ResponseResult;
import team.ybj.exception.*;
import team.ybj.pojo.*;
import team.ybj.service.*;



@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    LoginService loginService;

    @Autowired
    RegService regService;

    @Autowired
    PasswordService PasswordService;


    @PostMapping("login")
    @ResponseBody
    public ResponseResult login(@RequestBody YbjCustomer customer) {
        ResponseResult responseResult = loginService.login(customer);
        return responseResult;
    }

    @PostMapping("register")
    @ResponseBody
    public ResponseResult reg(@RequestBody YbjCustomer customer) {
        ResponseResult responseResult = new ResponseResult<>();
        try {
            responseResult = regService.reg(customer);
        }catch (UsernameDuplicatedException e){
            responseResult = new ResponseResult(400, "Duplicate username", 1);
        }catch (RuntimeException e) {
            responseResult = new ResponseResult(401, "Register failed for some unknown reason", 1);
        }
        return responseResult;
    }

        @PostMapping("passreset")
    @ResponseBody
    public ResponseResult PassReset(@RequestBody YbjCustomer customer) {
        ResponseResult responseResult;
        try {
            responseResult = PasswordService.PassReset(customer);
        }catch (UsernameDuplicatedException e){
            responseResult = new ResponseResult(400, "No Account Found", 0);
        }catch (ServiceException e) {
            responseResult = new ResponseResult(400, "Wrong security question or answer", 0);
        }catch (PassResetException e) {
            responseResult = new ResponseResult(400, "Something went wrong when trying to reset password", 0);
        }
        return responseResult;
    }
}
