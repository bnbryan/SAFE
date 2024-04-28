package team.ybj.service;

import team.ybj.dto.RegRequest;
import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjCustomer;

public interface RegService {

    public ResponseResult reg(RegRequest regRequest);

}
