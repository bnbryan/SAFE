package team.ybj.service;

import team.ybj.pojo.ResponseResult;
import team.ybj.pojo.YbjChecking;
import team.ybj.pojo.YbjCustomer;

public interface WithdrawService {
    public ResponseResult Withdraw(YbjChecking checking);
}
