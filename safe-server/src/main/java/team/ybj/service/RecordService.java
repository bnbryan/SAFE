package team.ybj.service;

import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjCustomer;
import team.ybj.pojo.YbjRecord;

public interface RecordService {

    public ResponseResult ListRe(YbjCustomer customer);
    public int AddRe(Long anum, Long toanum, String ratype, Double ramount);


}
