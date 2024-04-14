package team.ybj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ybj.mappers.AccountMapper;
import team.ybj.pojo.YbjAccount;
import team.ybj.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public YbjAccount findAccountById(Integer accountId) {
        YbjAccount account = accountMapper.getAccountById(accountId);
        return account;
    }

}
