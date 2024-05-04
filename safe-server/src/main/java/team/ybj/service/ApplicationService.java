package team.ybj.service;

import team.ybj.dto.ResponseResult;
import team.ybj.dto.UserGetAppsResponse;
import team.ybj.pojo.AccountApp;

import java.util.List;
import java.util.Map;

public interface ApplicationService {
    Long applyForAccount(AccountApp accountApp);

    List<UserGetAppsResponse> getUserApps(Long cid);

    Long rejectApp(Long appId);

    Long approveApp(Long appId);

    List<AccountApp> getAllApps();
}
