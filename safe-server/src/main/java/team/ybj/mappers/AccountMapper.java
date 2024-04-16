package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.ybj.pojo.YbjAccount;

import java.util.List;

@Repository
public interface AccountMapper {

    YbjAccount getAccountById(Integer id);

    List<YbjAccount> getAccountsByCid(Integer cid);

    int insertAccount(YbjAccount account);

    int updateAnameByAnum(@Param("anum") Integer anum, @Param("aname") String aname);

    int updateAdidByAnum(@Param("anum") Integer anum, @Param("adid") Integer adid);

    int deleteAccountByAnum(Integer anum);


}
