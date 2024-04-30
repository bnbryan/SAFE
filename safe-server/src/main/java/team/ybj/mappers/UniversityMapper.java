package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import team.ybj.pojo.YbjUniversity;

public interface UniversityMapper {
    YbjUniversity getUniversityByUid(Long uID);

    int insertUniversity(YbjUniversity university);

    YbjUniversity getUniversityByUname(String uname);

    int deleteUniversityByUid(Long uID);

}
