package team.ybj.mappers;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import team.ybj.pojo.YbjRecord;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Insert("INSERT INTO ybj_record (anum, toanum, ratype, ramount, rtime)"+"VALUES (#{anum}, #{toanum}, #{ratype}, #{ramount}, #{rtime})")
    @Options(useGeneratedKeys = true, keyProperty = "rid")
    int insertRecord(YbjRecord record);


    @Select("SELECT * FROM ybj_record WHERE rid = #{rid}")
    YbjRecord getRecordByRid(Long rid);

    @Select("SELECT * FROM ybj_record WHERE anum = #{anum}")
    List<YbjRecord> getRecordByAcc(Long anum);

    @Select("SELECT * FROM ybj_record WHERE toanum = #{toanum}")
    List<YbjRecord> getRecordByToAcc(Long toanum);

    @Delete("DELETE FROM ybj_record WHERE rid = #{rid}")
    int deleteRecordByRid(Long rid);
}
