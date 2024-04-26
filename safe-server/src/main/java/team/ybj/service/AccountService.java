package team.ybj.service;

import team.ybj.pojo.YbjAccount;

import java.util.List;

public interface AccountService {

    YbjAccount findAccountById(Long accountId);

    List<YbjAccount> findAllAccounts(Long cid);

    List<YbjAccount> findAllAccountsByEmail(String email);

}
