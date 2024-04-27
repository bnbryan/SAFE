package team.ybj.mappers;

import org.springframework.stereotype.Repository;
import team.ybj.pojo.AccountApp;

import java.util.List;

@Repository
public interface AccountAppMapper {
    int insertAccountApp(AccountApp accountApp);

    int deleteAccountAppById(Long appId);

    List<AccountApp> findAccountAppsByCid(Long cid);

    int updateAccountAppStatus(Long appId, Character status);
}
