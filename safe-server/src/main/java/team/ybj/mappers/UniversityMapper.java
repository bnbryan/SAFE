package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.ybj.pojo.YbjUniversity;

public interface UniversityMapper {
    YbjUniversity getUniversityByUid(Long uID);

    int insertUniversity(YbjUniversity university);

    YbjUniversity getUniversityByUname(String uname);

    int deleteUniversityByUid(Long uID);

    @Select("SELECT LAST_INSERT_ID()")
    Long getLastInsertId();
}
