package team.ybj.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.ResponseResult;
import team.ybj.pojo.AccountApp;
import team.ybj.pojo.YbjAccount;
import team.ybj.service.AccountService;
import team.ybj.service.ApplicationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Resource
    private ApplicationService applicationService;

    @GetMapping("getAccount")
    @ResponseBody
    public YbjAccount getAccount(@RequestParam("id") Long id) {
        System.out.println(id);
        YbjAccount account = accountService.findAccountById(id);
        return account;
    }

    @GetMapping("/all/{cid}")
    @ResponseBody
    public ResponseResult<List<YbjAccount>> getAllAccount(@PathVariable("cid") Long cid) {
        List<YbjAccount> allAccountsOfCustomer = accountService.findAllAccounts(cid);
        return new ResponseResult<>(200, "success", allAccountsOfCustomer);
    }

    @PostMapping("/apply")
    @ResponseBody
    public ResponseResult<Map<String, Long>> applyAccount(@RequestBody AccountApp app) {
        Map<String, Long> result = applicationService.applyForAccount(app);
        return new ResponseResult<>(200, "success", result);
    }

    @GetMapping("test")
    public Integer test() {
        return 1;
    }
}
