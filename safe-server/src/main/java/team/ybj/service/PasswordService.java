package team.ybj.service;

import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjCustomer;

public interface PasswordService {
    public ResponseResult PassReset(YbjCustomer customer);
}
