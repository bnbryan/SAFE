package team.ybj.controller;


import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.RejectAppRequest;
import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjAdmin;
import team.ybj.service.AdminService;
import team.ybj.service.ApplicationService;

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

    @GetMapping("test")
    public int test() {
        return 1;
    }
}
