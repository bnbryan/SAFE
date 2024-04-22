package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.exception.UsernameDuplicatedException;
import team.ybj.pojo.ResponseResult;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.LoginService;
import team.ybj.service.RegService;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    LoginService loginService;

    @Autowired
    RegService regService;

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
}
