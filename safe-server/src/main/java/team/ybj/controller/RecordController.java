package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.ResponseResult;
import team.ybj.exception.*;
import team.ybj.pojo.*;
import team.ybj.service.*;


@RestController
@RequestMapping("users")
public class RecordController {

    @Autowired
    RecordService recordService;

    @PostMapping("records")
    @ResponseBody
    public ResponseResult ListRe(@RequestBody YbjCustomer customer) {
        ResponseResult responseResult;
        try {
            responseResult = recordService.ListRe(customer);
        }catch (LackBalanceException e){
            responseResult = new ResponseResult(400, "Balance not enough", 0);
        }catch (ServiceException e) {
            responseResult = new ResponseResult(400, "Something went wrong when withdrawing", 0);
        }
        return responseResult;
    }
}
