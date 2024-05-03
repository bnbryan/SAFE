package team.ybj.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.dto.UserGetAppsResponse;
import team.ybj.exception.AccountTypeException;
import team.ybj.exception.NoDataException;
import team.ybj.exception.ServiceException;
import team.ybj.mappers.*;
import team.ybj.pojo.*;
import team.ybj.service.LoanAppService;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
    @Resource
    private InsuranceMapper insuranceMapper;
    @Resource
    private CompanyMapper companyMapper;


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
    @Cacheable("loanApps")
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
    public List<YbjLoanApp> getAllLoanApps(){
        List<YbjLoanApp> loanApps = loanAppMapper.findAllLoanApp();
        return loanApps;
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
        Date currentDate = new Date();
        Instant instant = currentDate.toInstant();
        instant = instant.plus(-1, ChronoUnit.SECONDS); // 加上一秒
        Date now = Date.from(instant);
        if(loanApp.getLtype() == 'S') {
            Long uID;
            if(universityMapper.getUniversityByUname(loanApp.getUname()) == null){
                YbjUniversity university = new YbjUniversity(null, loanApp.getUname());
                universityMapper.insertUniversity(university);
                uID = universityMapper.getLastInsertId();
            }else{
                uID = universityMapper.getUniversityByUname(loanApp.getUname()).getUID();
            }

            YbjAccount account = new YbjAccount(null,
                        customerMapper.getCustomerByCid(loanApp.getCid()).getCfname()+" Loan", now,
                        'L', loanApp.getCid().longValue(), cusAddLinkMapper.getAddressIdsByCustomerId(loanApp.getCid()).get(0));
            accountMapper.insertAccount(account);
            Long anum = account.getAnum();
            YbjLoan loan = new YbjLoan(anum, loanApp.getLrate(), loanApp.getLamount(), loanApp.getLmonths(),
                    loanApp.getLpayment(), "STU", null, null, null, loanApp.getStuid(),
                    loanApp.getStutype(), loanApp.getStugraddate(), uID, 'L', 'Y');
            try{loanMapper.insertLoan(loan);
            loanAppMapper.updateLoanAppStatus(laid, 'P');}
            catch(Exception e){throw new ServiceException(e.getMessage());}
            return anum;
        }
        else {
            if(insuranceMapper.getInsuranceByAccount(loanApp.getLaiaccount()) == null){
                YbjInsurance insurance = new YbjInsurance(null, loanApp.getLaiaccount(), BigDecimal.valueOf(loanApp.getIpremium()),
                        companyMapper.getCompanyByCom(loanApp.getLacomname()).getComid());
                insuranceMapper.insertInsurance(insurance);
                YbjAccount account = new YbjAccount(null,
                        customerMapper.getCustomerByCid(loanApp.getCid()).getCfname()+" Loan", now,
                        'L', loanApp.getCid(), cusAddLinkMapper.getAddressIdsByCustomerId(loanApp.getCid()).get(0));
                accountMapper.insertAccount(account);
                Long anum = account.getAnum();
                YbjLoan loan = new YbjLoan(anum, loanApp.getLrate(), loanApp.getLamount(), loanApp.getLmonths(),
                    loanApp.getLpayment(), "HOME", loanApp.getHyear(), loanApp.getHinsurance(), insurance.getIid(), null,
                    null, null, null, 'L', 'Y');
            try{loanMapper.insertLoan(loan);
            loanAppMapper.updateLoanAppStatus(laid, 'P');}
            catch(Exception e){throw new ServiceException(e.getMessage());}
            return anum;
            }else{
                throw new ServiceException("Insurance already exist");
            }
        }
    }
}
