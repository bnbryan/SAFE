package team.ybj.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.dto.AccountDetail;
import team.ybj.dto.ApproveAccountRequest;
import team.ybj.exception.AccountTypeException;
import team.ybj.mappers.*;
import team.ybj.pojo.YbjAccount;
import team.ybj.pojo.YbjChecking;
import team.ybj.pojo.YbjLoan;
import team.ybj.pojo.YbjSaving;
import team.ybj.service.AccountService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Resource
    private CheckingMapper checkingMapper;
    @Resource
    private SavingMapper savingMapper;
    @Resource
    private LoanMapper loanMapper;
    @Resource
    private CusAddLinkMapper cusAddLinkMapper;

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
    public List<AccountDetail> findAllAccountsByEmail(String email) {
        List<YbjAccount> accounts = accountMapper.getAccountsByEmail(email);
        List<AccountDetail> accountDetails = new ArrayList<>();

        for (YbjAccount account : accounts) {
            if (account.getAtype() == 'C') {
                YbjChecking checking = checkingMapper.getCheckingByAnum(account.getAnum());
                accountDetails.add(
                        new AccountDetail(account, checking.getAbalance(), null, checking.getCcharge(), null)
                );
            } else if (account.getAtype() == 'S') {
                YbjSaving saving = savingMapper.getSavingByAnum(account.getAnum());
                accountDetails.add(
                        new AccountDetail(account, saving.getSbalance(), saving.getSrate(), null, null)
                );
            } else {
                YbjLoan loan  = loanMapper.getLoanByAnum(account.getAnum());
                accountDetails.add(
                        new AccountDetail(account, loan.getLamount(), loan.getLrate(), null, loan.getLtype())
                );
            }
        }
        return accountDetails;
    }

    @Override
    @Transactional
    public Long insertAccount(ApproveAccountRequest request) {
        Character type = request.getType();
        request.setAname(type == 'C' ? "SAFE Checking" : "SAFE Savings");

        // adid 查寻找
        Long adid = cusAddLinkMapper.getAddressIdsByCustomerId(request.getCid()).get(0);
        request.setAdid(adid);

        YbjAccount account =
                new YbjAccount(request.getAname(), request.getAdate(), type, request.getCid(), request.getAdid());
        accountMapper.insertAccount(account);
        if (type == 'C') {
            YbjChecking checking = new YbjChecking(account.getAnum(), request.getCcharge(), 'C', 0.00, 'Y');
            checkingMapper.insertChecking(checking);
            return account.getAnum();
        } else if (type == 'S') {
            YbjSaving saving = new YbjSaving(account.getAnum(), request.getSrate(), 'S', 0.00, 'Y');
            savingMapper.insertSaving(saving);
            return account.getAnum();
        } else {
            throw new AccountTypeException("Account type not supported");
        }
    }


}
