package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjAccount;
import team.ybj.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;

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

    @GetMapping("test")
    public Integer test() {
        return 1;
    }
}
