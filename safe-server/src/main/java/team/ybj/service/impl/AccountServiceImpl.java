package team.ybj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ybj.mappers.AccountMapper;
import team.ybj.pojo.YbjAccount;
import team.ybj.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public YbjAccount findAccountById(Long accountId) {
        YbjAccount account = accountMapper.getAccountById(accountId);
        return account;
    }

    @Override
    public List<YbjAccount> findAllAccounts(Long cid) {
        return accountMapper.getAccountsByCid(cid);
    }

    @Override
    public List<YbjAccount> findAllAccountsByEmail(String email) {
        return accountMapper.getAccountsByEmail(email);
    }


}
