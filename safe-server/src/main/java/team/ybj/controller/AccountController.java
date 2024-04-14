package team.ybj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.pojo.YbjAccount;
import team.ybj.service.impl.AccountServiceImpl;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("getAccount")
    @ResponseBody
    public YbjAccount getAccount(@RequestParam("id") Integer id) {
        System.out.println(id);
        YbjAccount account = accountService.findAccountById(id);
        return account;
    }

    @GetMapping("test")
    public Integer test() {
        return 1;
    }
}
