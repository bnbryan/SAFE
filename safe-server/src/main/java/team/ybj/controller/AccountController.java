package team.ybj.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.AccountDetail;
import team.ybj.dto.ResponseResult;
import team.ybj.dto.UserGetAppsResponse;
import team.ybj.exception.ServiceException;
import team.ybj.exception.accountException;
import team.ybj.pojo.AccountApp;
import team.ybj.pojo.YbjAccount;
import team.ybj.pojo.YbjLoanApp;
import team.ybj.service.AccountService;
import team.ybj.service.ApplicationService;
import team.ybj.service.LoanAppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("account")
public class AccountController {
    @Resource
    private AccountService accountService;
    @Resource
    private ApplicationService applicationService;
    @Resource
    private LoanAppService loanAppService;


    @GetMapping("getAccount")
    @ResponseBody
    public YbjAccount getAccount(@RequestParam("id") Long id) {
        System.out.println(id);
        YbjAccount account = accountService.findAccountById(id);
        return account;
    }

    @GetMapping("/all/{email}")
    @ResponseBody
    public ResponseResult<List<AccountDetail>> getAllAccount(@PathVariable("email") String email) {
        List<AccountDetail> allAccountsOfCustomer = accountService.findAllAccountsByEmail(email);
        return new ResponseResult<>(200, "success", allAccountsOfCustomer);
    }

    @PostMapping("/app")
    @ResponseBody
    public ResponseResult<Map<String, Long>> applyAccount(@RequestBody AccountApp app) {
        Long appId = applicationService.applyForAccount(app);
        Map<String, Long> data = new HashMap<>();
        data.put("appId", appId);
        return new ResponseResult<>(200, "success", data);
    }

    @PostMapping("/apploan")
    @ResponseBody
    public ResponseResult<Map<String, Long>> applyLoanAccount(@RequestBody YbjLoanApp loanApp) {
        Long laid = loanAppService.applyForLoan(loanApp);
        Map<String, Long> data = new HashMap<>();
        data.put("laid", laid);
        return new ResponseResult<>(200, "success", data);
    }

    @GetMapping("/app/{cid}")
    @ResponseBody
    public ResponseResult<Map<String, List<UserGetAppsResponse>>> getAllActiveApps(@PathVariable Long cid) {
        List<UserGetAppsResponse> userApps = applicationService.getUserApps(cid);
        Map<String, List<UserGetAppsResponse>> data = new HashMap<>();
        data.put("userApps", userApps);
        return new ResponseResult<>(200, "success", data);
    }

    @GetMapping("/apploan/{cid}")
    @ResponseBody
    public ResponseResult<Map<String, List<UserGetAppsResponse>>> getAllActiveLoanApps(@PathVariable Long cid) {

        List<UserGetAppsResponse> userApps = loanAppService.getUserLoanApps(cid);
        Map<String, List<UserGetAppsResponse>> data = new HashMap<>();
        data.put("userLoanApps", userApps);
        return new ResponseResult<>(200, "success", data);
    }

    @GetMapping("test")
    public Integer test() {
        return 1;
    }
}
