package team.ybj.service;

import team.ybj.pojo.YbjAdmin;

import java.util.Map;

public interface AdminService {
    Map<String, String> login(YbjAdmin admin);
}
