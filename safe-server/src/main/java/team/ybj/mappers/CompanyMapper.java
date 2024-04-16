package team.ybj.mappers;

import org.apache.ibatis.annotations.*;
import team.ybj.pojo.YbjCompany;

import java.util.List;
@Mapper
public interface CompanyMapper {
    //为了使用直观，我用annotation把sql写出来而不是在xml文件里写。

    // Create
    @Options(useGeneratedKeys = true, keyProperty = "comid")
    @Insert("INSERT INTO ybj_company (comname, adid) VALUES (#{comname}, #{adid})")
    int insert(YbjCompany company);

    @Insert("INSERT INTO ybj_company (comid,comname, adid) VALUES (#{comid},#{comname}, #{adid})")
    int insertWithId(YbjCompany company);

    // Read
    @Select("SELECT comid, comname, adid FROM ybj_company WHERE comid = #{comid}")
    YbjCompany selectById(Long comid);

    @Select("SELECT comid, comname, adid FROM ybj_company")
    List<YbjCompany> selectAll();

    // Update
    @Update("UPDATE ybj_company SET comname = #{comname}, adid = #{adid} WHERE comid = #{comid}")
    int update(YbjCompany company);

    // Delete
    @Delete("DELETE FROM ybj_company WHERE comid = #{comid}")
    int deleteById(Long comid);
}
