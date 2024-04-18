package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import team.ybj.pojo.YbjSaving;

public interface SavingMapper {
    YbjSaving getSavingByAnum(Long anum);

    int insertSaving(YbjSaving saving);

    int updateSrateByAnum(@Param("anum") Long anum, @Param("srate") Double srate);

    int updateSbalanceByAnum(@Param("anum") Long anum, @Param("sbalance") Double sbalance);

    int updateSvalidByAnum(@Param("anum") Long anum, @Param("svalid") Character svalid);

    int deleteSavingByAnum(Long anum);


}
