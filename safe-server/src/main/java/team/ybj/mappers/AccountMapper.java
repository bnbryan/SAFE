package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.ybj.pojo.YbjAccount;

import java.util.List;

@Repository
public interface AccountMapper {

    YbjAccount getAccountById(Long id);

    List<YbjAccount> getAccountsByCid(Long cid);

    int insertAccount(YbjAccount account);

    int updateAnameByAnum(@Param("anum") Long anum, @Param("aname") String aname);

    int updateAdidByAnum(@Param("anum") Long anum, @Param("adid") Long adid);

    int deleteAccountByAnum(Long anum);

}
