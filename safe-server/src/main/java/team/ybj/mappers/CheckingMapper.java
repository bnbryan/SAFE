package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import team.ybj.pojo.YbjChecking;

public interface CheckingMapper {

    YbjChecking getCheckingByAnum(Long Anum);

    int insertChecking(YbjChecking checking);

    int updateCchargeByAnum(@Param("anum") Long anum, @Param("ccharge") Double ccharge);

    int updateAbalanceByAnum(@Param("anum") Long anum, @Param("abalance") Double abalance);

    int updateCvalidByAnum(@Param("anum") Long anum, @Param("cvalid") Character cvalid);

    int deleteCheckingByAnum(Long anum);

}
