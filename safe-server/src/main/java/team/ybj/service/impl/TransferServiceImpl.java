package team.ybj.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.mappers.AccountMapper;
import team.ybj.mappers.CheckingMapper;
import team.ybj.mappers.SavingMapper;
import team.ybj.pojo.TransferRequest;
import team.ybj.pojo.YbjAccount;
import team.ybj.pojo.YbjChecking;
import team.ybj.pojo.YbjSaving;
import team.ybj.service.TransferService;

@Service
public class TransferServiceImpl implements TransferService {
    @Resource
    AccountMapper accountMapper;
    @Resource
    CheckingMapper checkingMapper;
    @Resource
    SavingMapper savingMapper;

    @Override
    @Transactional
    public int transfer(TransferRequest transferRequest) {
        Long fromAccountNum = transferRequest.getFromAccountNum();
        Long toAccountNum = transferRequest.getToAccountNum();
        Double amount = transferRequest.getAmount();
        Character fromType = transferRequest.getFromAccountType();

        if (fromType == 'C') {
            return transferFromChecking(fromAccountNum, toAccountNum, amount);
        } else if (fromType == 'S') {
            return transferFromSaving(fromAccountNum, toAccountNum, amount);
        } else {
            return -1;
        }
    }

    @Override
    @Transactional
    public int transferFromChecking(Long fromAccountNum, Long toAccountNum, Double amount) {
        YbjChecking fromAccount = checkingMapper.getCheckingByAnum(fromAccountNum);
        Character toAccountType = getAccountType(toAccountNum);
        int fromSuccess, toSuccess;
        if (fromAccount.getAbalance() < amount) {
            return -1;
//            throw new RuntimeException("not enough money");
        }
        fromSuccess = checkingMapper.updateAbalanceByAnum(fromAccountNum, fromAccount.getAbalance() - amount);
        if (toAccountType == 'C') {
            YbjChecking toAccount = checkingMapper.getCheckingByAnum(toAccountNum);
            toSuccess = checkingMapper.updateAbalanceByAnum(toAccountNum, toAccount.getAbalance() + amount);
        } else if (toAccountType == 'S') {
            YbjSaving toAccount = savingMapper.getSavingByAnum(toAccountNum);
            toSuccess = savingMapper.updateSbalanceByAnum(toAccountNum, toAccount.getSbalance() + amount);
        } else {
            return -1;
        }
        return fromSuccess + toSuccess;
    }

    @Override
    @Transactional
    public int transferFromSaving(Long fromAccountNum, Long toAccountNum, Double amount) {
        YbjSaving fromAccount = savingMapper.getSavingByAnum(fromAccountNum);
        Character toAccountType = getAccountType(toAccountNum);
        int fromSuccess, toSuccess;
        if (fromAccount.getSbalance() < amount) {
            return -1;
        }
        fromSuccess = savingMapper.updateSbalanceByAnum(fromAccountNum, fromAccount.getSbalance() - amount);
        if (toAccountType == 'C') {
            YbjChecking toAccount = checkingMapper.getCheckingByAnum(toAccountNum);
            toSuccess = checkingMapper.updateAbalanceByAnum(toAccountNum, toAccount.getAbalance() + amount);
        } else if (toAccountType == 'S') {
            YbjSaving toAccount = savingMapper.getSavingByAnum(toAccountNum);
            toSuccess = savingMapper.updateSbalanceByAnum(toAccountNum, toAccount.getSbalance() + amount);
        } else {
            return -1;
        }
        return fromSuccess + toSuccess;
    }

    public Character getAccountType(Long accountNum) {
        YbjAccount account = accountMapper.getAccountById(accountNum);
        return account.getAtype();
    }
}
