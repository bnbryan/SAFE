package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team.ybj.pojo.ResponseResult;
import team.ybj.pojo.YbjCustomer;
import team.ybj.service.RegService;


@RestController
public class RegController {

    @Autowired
    RegService regService;

    @PostMapping("users")
    @ResponseBody
    public ResponseResult reg(@RequestBody YbjCustomer customer) {
        ResponseResult responseResult = new ResponseResult<>();
        try{
            responseResult = regService.reg(customer);
        }catch (Exception e) {
            responseResult = new ResponseResult(400, "Register failed", 1);
        }
        return responseResult;
    }
}
