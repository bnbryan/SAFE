package team.ybj.mappers;

import org.springframework.stereotype.Repository;
import team.ybj.pojo.YbjAccount;

@Repository
public interface AccountMapper {
    YbjAccount getAccountById(Integer id);

}
