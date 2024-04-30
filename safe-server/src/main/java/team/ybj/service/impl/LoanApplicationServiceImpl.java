package team.ybj.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.dto.UserGetAppsResponse;
import team.ybj.exception.AccountTypeException;
import team.ybj.exception.NoDataException;
import team.ybj.exception.ServiceException;
import team.ybj.mappers.*;
import team.ybj.pojo.*;
import team.ybj.service.LoanAppService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoanApplicationServiceImpl implements LoanAppService {

    @Resource
    private LoanAppMapper loanAppMapper;
    @Resource
    private LoanMapper loanMapper;
    @Resource
    private UniversityMapper universityMapper;
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private CusAddLinkMapper cusAddLinkMapper;
    @Resource
    private AccountMapper accountMapper;

    @Override
    @Transactional
    public Long applyForLoan(YbjLoanApp loanApp) {
        int success = loanAppMapper.insertLoanApp(loanApp);
        if (success > 0) {
            return loanApp.getLaid();
        } else {
            throw new RuntimeException("Application submission failed");
        }
    }

    @Override
    public List<UserGetAppsResponse> getUserLoanApps(Long cid) {
        List<YbjLoanApp> loanApps = loanAppMapper.findLoanAppsByCid(cid);
        if (loanApps == null || loanApps.isEmpty()) {
            throw new NoDataException("No active applications found");
        }
        List<UserGetAppsResponse> userGetAppsResponses = new ArrayList<>();
        for (YbjLoanApp loanApp : loanApps) {
            if (loanApp.getLavalid() == null || loanApp.getLavalid() == 'D') {
                UserGetAppsResponse userGetAppsResponse =
                        new UserGetAppsResponse(loanApp.getLaid(), loanApp.getLtype(), loanApp.getLavalid());
                userGetAppsResponses.add(userGetAppsResponse);
            }
        }
        return userGetAppsResponses;
    }

    @Override
    public Long rejectLoanApp(Long laid) {
        int success = loanAppMapper.updateLoanAppStatus(laid, 'D');
        if (success > 0) {
            return laid;
        } else {
            throw new ServiceException("Application status change failed");
        }
    }

    @Override
    @Transactional
    public Long acceptLoanApp(Long laid) {
        YbjLoanApp loanApp = loanAppMapper.findLoanAppByLaid(laid);
        if(loanApp.getLavalid() == 'S') {
            Long uID = 0L;
            if(universityMapper.getUniversityByUname(loanApp.getUname()) == null){
                YbjUniversity university = new YbjUniversity();
                university.setUname(loanApp.getUname());
                universityMapper.insertUniversity(university);
                uID = university.getUID();
            }
            YbjAccount account = new YbjAccount(null,
                        customerMapper.getCustomerByCid(loanApp.getCid()).getCfname()+" Loan", new Date(),
                        'L', loanApp.getCid(), cusAddLinkMapper.getAddressIdsByCustomerId(loanApp.getCid()).get(0));
            accountMapper.insertAccount(account);
            Long anum = account.getAnum();
            YbjLoan loan = new YbjLoan(anum, loanApp.getLrate(), loanApp.getLamount(), loanApp.getLmonths(), loanApp.getLpayment(), "STU", null, null, null, loanApp.getStuid(), loanApp.getStutype(), loanApp.getStugraddate(), uID, 'L', 'P');
            try{loanMapper.insertLoan(loan);}
            catch(Exception e){throw new ServiceException(e.getMessage());}
            return laid;
        }

        return laid;
    }
}
