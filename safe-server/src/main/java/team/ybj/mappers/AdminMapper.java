package team.ybj.mappers;

import team.ybj.pojo.YbjAdmin;

public interface AdminMapper {
    YbjAdmin findAdminByUsername(String username);
}
