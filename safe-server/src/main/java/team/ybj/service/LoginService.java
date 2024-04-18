package team.ybj.service;

import team.ybj.pojo.ResponseResult;
import team.ybj.pojo.YbjCustomer;

public interface LoginService {

    public ResponseResult login(YbjCustomer customer);

}
