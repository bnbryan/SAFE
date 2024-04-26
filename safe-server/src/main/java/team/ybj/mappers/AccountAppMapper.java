package team.ybj.mappers;

import org.springframework.stereotype.Repository;
import team.ybj.pojo.AccountApp;

@Repository
public interface AccountAppMapper {
    int insertAccountApp(AccountApp accountApp);

    int deleteAccountAppById(Long appId);
}
