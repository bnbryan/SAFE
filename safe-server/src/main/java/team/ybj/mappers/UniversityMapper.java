package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import team.ybj.pojo.YbjUniversity;

public interface UniversityMapper {
    YbjUniversity getUniversityByUid(Long uID);

    int insertUniversity(YbjUniversity university);

    int deleteUniversityByUid(Long uID);

}
