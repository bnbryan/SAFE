package team.ybj.controller;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.ApproveAccountRequest;
import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjAdmin;
import team.ybj.pojo.YbjLoanApp;
import team.ybj.service.AccountService;
import team.ybj.service.AdminService;
import team.ybj.service.ApplicationService;
import team.ybj.service.LoanAppService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Resource
    AdminService adminService;
    @Resource
    ApplicationService applicationService;
    @Resource
    LoanAppService loanAppService;
    @Resource
    AccountService accountService;


    @PostMapping("login")
    @ResponseBody
    public ResponseResult<Map<String, String>> login(@RequestBody YbjAdmin admin) {
        Map<String, String> response = adminService.login(admin);
        return new ResponseResult<>(200, "success", response);
    }

    @PostMapping("/app/reject")
    @ResponseBody
    public ResponseResult<Map<String, Long>> rejectApp(@RequestBody JsonNode node) {
        Long appId = node.get("appId").asLong();
        Long rejectedApp = applicationService.rejectApp(appId);
        Map<String, Long> data = new HashMap<>();
        data.put("rejectedApp", rejectedApp);
        return new ResponseResult<>(200, "success", data);
    }

    @PostMapping("/app/approve")
    @ResponseBody
    public ResponseResult<Map<String, Long>> approveApp(@RequestBody ApproveAccountRequest request) {
        Long appId = applicationService.approveApp(request.getAppId());
        Long newAnum = accountService.insertAccount(request);
        Map<String, Long> data = new HashMap<>();
        data.put("AccountNumber", newAnum);
        data.put("ApprovedApp", appId);
        return new ResponseResult<>(200, "success", data);
    }

    @PostMapping("/apploan/reject")
    @ResponseBody
    public ResponseResult<Map<String, Long>> rejectLoanApp(@RequestBody YbjLoanApp loanApp) {
        Long laid = loanApp.getLaid();
        Long rejectedApp = loanAppService.rejectLoanApp(laid);
        Map<String, Long> data = new HashMap<>();
        data.put("rejectedApp", rejectedApp);
        return new ResponseResult<>(200, "success", data);
    }

    @PostMapping("/apploan/approve")
    @ResponseBody
    public ResponseResult<Map<String, Long>> acceptLoanApp(@RequestBody YbjLoanApp loanApp) {
        Long acceptedApp = loanAppService.acceptLoanApp(loanApp.getLaid());
        Map<String, Long> data = new HashMap<>();
        data.put("AccountNumber", acceptedApp);
        data.put("ApprovedApp", loanApp.getLaid());
        return new ResponseResult<>(200, "success", data);
    }

    @GetMapping("apploan/")
    @ResponseBody
    public ResponseResult getAllLoanApp() {
        List<YbjLoanApp> loanApps = loanAppService.getAllLoanApps();
        return new ResponseResult<>(200, "success", loanApps);
    }

    @GetMapping("test")
    public int test() {
        return 1;
    }
}
