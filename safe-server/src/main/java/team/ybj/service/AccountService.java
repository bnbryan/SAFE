package team.ybj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ybj.mappers.AccountMapper;
import team.ybj.pojo.YbjAccount;

@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public YbjAccount findAccountById(Integer accountId) {
        YbjAccount account = accountMapper.getAccountById(accountId);
        return account;
    }

}
