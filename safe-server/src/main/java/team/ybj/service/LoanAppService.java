package team.ybj.service;

import org.springframework.stereotype.Service;
import team.ybj.dto.TransferRequest;
import team.ybj.dto.UserGetAppsResponse;
import team.ybj.pojo.YbjLoanApp;

import java.util.List;

public interface LoanAppService {

    Long applyForLoan(YbjLoanApp loanApp);

    List<UserGetAppsResponse> getUserLoanApps(Long cid);

    Long rejectLoanApp(Long laid);

    Long acceptLoanApp(Long laid);

    List<YbjLoanApp> getAllLoanApps();

}
