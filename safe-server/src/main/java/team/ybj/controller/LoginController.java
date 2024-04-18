package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team.ybj.pojo.ResponseResult;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.LoginService;


@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("login")
    @ResponseBody
    public ResponseResult login(@RequestBody YbjCustomer customer) {
        ResponseResult responseResult = loginService.login(customer);
        return responseResult;
    }
    
}
