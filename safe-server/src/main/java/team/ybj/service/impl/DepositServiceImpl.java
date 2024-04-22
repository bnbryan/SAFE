package team.ybj.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import team.ybj.dto.DepositRequest;
import team.ybj.exception.AccountTypeException;
import team.ybj.exception.DepositNegativeException;
import team.ybj.mappers.AccountMapper;
import team.ybj.mappers.CheckingMapper;
import team.ybj.mappers.SavingMapper;
import team.ybj.pojo.YbjAccount;
import team.ybj.pojo.YbjChecking;
import team.ybj.pojo.YbjSaving;
import team.ybj.service.DepositService;

@Service
public class DepositServiceImpl implements DepositService {

    @Resource
    AccountMapper accountMapper;
    @Resource
    CheckingMapper checkingMapper;
    @Resource
    SavingMapper savingMapper;

    @Override
    public Double deposit(DepositRequest depositRequest) {
        Long accountNum = depositRequest.getAccountNum();
        Double depositAmount = depositRequest.getAmount();

        if (depositAmount <= 0) {
            throw new DepositNegativeException("Deposit amount must be greater than 0");
        }

        Character type = getAccountType(accountNum);
        Double newBalance;

        if (type == 'C') {
            YbjChecking account = checkingMapper.getCheckingByAnum(accountNum);
            newBalance = account.getAbalance() + depositAmount;
            checkingMapper.updateAbalanceByAnum(accountNum, newBalance);
            return newBalance;
        } else if (type == 'S') {
            YbjSaving account = savingMapper.getSavingByAnum(accountNum);
            newBalance = account.getSbalance() + depositAmount;
            savingMapper.updateSbalanceByAnum(accountNum, newBalance);
            return newBalance;
        } else {
            throw new AccountTypeException("Invalid Account Type");
        }
    }

    public Character getAccountType(Long accountNum) {
        YbjAccount account = accountMapper.getAccountById(accountNum);
        return account.getAtype();
    }
}
