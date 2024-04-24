package team.ybj.controller;


import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import team.ybj.dto.ResponseResult;
import team.ybj.pojo.YbjAdmin;
import team.ybj.service.AdminService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Resource
    AdminService adminService;

    @PostMapping("login")
    @ResponseBody
    public ResponseResult<Map<String, String>> login(@RequestBody YbjAdmin admin) {
        Map<String, String> response = adminService.login(admin);
        return new ResponseResult<>(200, "success", response);
    }

    @GetMapping("test")
    public int test() {
        return 1;
    }
}
