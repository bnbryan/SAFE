package team.ybj.service;

import team.ybj.pojo.ResponseResult;
import team.ybj.pojo.YbjCustomer;

public interface PasswordService {
    public ResponseResult PassReset(YbjCustomer customer);
}
