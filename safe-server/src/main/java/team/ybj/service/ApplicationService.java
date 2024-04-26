package team.ybj.service;

import team.ybj.dto.ResponseResult;
import team.ybj.pojo.AccountApp;

import java.util.Map;

public interface ApplicationService {
    Map<String, Long> applyForAccount(AccountApp accountApp);
}
