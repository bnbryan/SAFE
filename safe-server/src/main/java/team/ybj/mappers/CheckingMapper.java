package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import team.ybj.pojo.YbjChecking;

public interface CheckingMapper {

    YbjChecking getCheckingByAnum(String Anum);

    int insertChecking(YbjChecking checking);

    int updateCchargeByAnum(@Param("anum") Integer anum, @Param("ccharge") Double ccharge);

    int updateAbalanceByAnum(@Param("anum") Integer anum, @Param("abalance") Double abalance);

    int updateCvalidByAnum(@Param("anum") Integer anum, @Param("cvalid") Character cvalid);

    int deleteCheckingByAnum(Integer anum);

}
