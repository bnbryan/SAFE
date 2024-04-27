package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.ResponseResult;
import team.ybj.exception.*;
import team.ybj.pojo.*;
import team.ybj.service.*;

import java.util.Map;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    LoginService loginService;

    @Autowired
    RegService regService;

    @Autowired
    PasswordService PasswordService;

    @Autowired
    DelAccService DelAccService;


    @PostMapping("login")
    @ResponseBody
    public ResponseResult<Map<String, String>> login(@RequestBody YbjCustomer customer) {
        return loginService.login(customer);
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

    @PostMapping("delacc")
    @ResponseBody
    public ResponseResult DelAcc(@RequestBody YbjCustomer customer) {
        ResponseResult responseResult;
        try {
            responseResult = DelAccService.DelAcc(customer);
        }catch (AccountTypeException e){
            responseResult = new ResponseResult(400, "No Account Found", 0);
        }catch (ServiceException e) {
            responseResult = new ResponseResult(400, "Something went wrong when trying to delete account", 0);
        }
        return responseResult;
    }
}
