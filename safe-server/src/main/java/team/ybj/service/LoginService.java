package team.ybj.service;

import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjCustomer;

import java.util.Map;

public interface LoginService {

    public ResponseResult<Map<String, String>> login(YbjCustomer customer);

}
