package team.ybj.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.dto.ResponseResult;
import team.ybj.exception.AccountTypeException;
import team.ybj.mappers.AccountAppMapper;
import team.ybj.mappers.AccountMapper;
import team.ybj.mappers.CheckingMapper;
import team.ybj.pojo.AccountApp;
import team.ybj.pojo.YbjAccount;
import team.ybj.service.ApplicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountAppMapper accountAppMapper;

    @Override
    @Transactional
    public Map<String, Long> applyForAccount(AccountApp accountApp) {
        // check if account already exist
        Character type = accountApp.getType();
        List<YbjAccount> accountsExists = accountMapper.getAccountsByCid(accountApp.getCid());

        for (YbjAccount account : accountsExists) {
            if (account.getAtype() == type) {
                // the applicant already has the same type account
                throw new AccountTypeException("Account already exists");
            }
        }
        int success = accountAppMapper.insertAccountApp(accountApp);
        if (success > 0) {
            Map<String, Long> resultMap = new HashMap<>();
            resultMap.put("appId", accountApp.getAppId());
            return resultMap;
        } else {
            throw new RuntimeException("Application submission failed");
        }
    }
}
