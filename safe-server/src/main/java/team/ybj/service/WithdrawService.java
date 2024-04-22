package team.ybj.service;

import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjChecking;

public interface WithdrawService {
    public ResponseResult Withdraw(YbjChecking checking);
}
