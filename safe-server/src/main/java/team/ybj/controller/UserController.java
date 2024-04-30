package team.ybj.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.CustomerBasic;
import team.ybj.dto.RegRequest;
import team.ybj.dto.ResponseResult;
import team.ybj.exception.*;
import team.ybj.pojo.*;
import team.ybj.service.*;

import java.util.HashMap;
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

    @Autowired
    RecordService recordService;

    @Resource
    UserService userService;

    @PostMapping("login")
    @ResponseBody
    public ResponseResult<Map<String, String>> login(@RequestBody YbjCustomer customer) {
        return loginService.login(customer);
    }

    @PostMapping("register")
    @ResponseBody
    public ResponseResult reg(@RequestBody RegRequest regRequest) {
        ResponseResult responseResult = new ResponseResult<>();
        try {
            responseResult = regService.reg(regRequest);
        }catch (UsernameDuplicatedException e){
            responseResult = new ResponseResult(400, "Duplicate username", 1);
        }catch (ServiceException e) {
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

    @GetMapping("records")
    @ResponseBody
    public ResponseResult ListRe(@RequestParam("email") String email) {
        ResponseResult responseResult;
        try {
            responseResult = recordService.ListRe(email);
        }catch (LackBalanceException e){
            responseResult = new ResponseResult(400, "Balance not enough", 0);
        }catch (ServiceException e) {
            responseResult = new ResponseResult(400, "Something went wrong when withdrawing", 0);
        }
        return responseResult;
    }

    @GetMapping("/email/{email}")
    @ResponseBody
    public ResponseResult<Map<String, CustomerBasic>> findCustomerByEmail(@PathVariable("email") String email) {
        CustomerBasic customer = userService.findCustomerByEmail(email);
        Map<String, CustomerBasic> data = new HashMap<>();
        data.put("customer", customer);
        return new ResponseResult<>(200, "OK", data);
    }
}
