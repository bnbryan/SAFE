package team.ybj.service;

import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjCustomer;

public interface LoginService {

    public ResponseResult login(YbjCustomer customer);

}
