package team.ybj.mappers;
import org.apache.ibatis.annotations.*;
import team.ybj.pojo.YbjInsurance;

import java.util.List;

@Mapper
public interface InsuranceMapper {

    // Create
    @Insert("INSERT INTO ybj_insurance (iaccount, ipremium, comid) VALUES (#{iaccount}, #{ipremium}, #{comid})")
    @Options(useGeneratedKeys = true, keyProperty = "iid")
    int insertInsurance(YbjInsurance insurance);

    // Read by ID
    @Select("SELECT * FROM ybj_insurance WHERE iid = #{iid}")
    YbjInsurance getInsuranceByLid(Long lid);

    @Select("SELECT * FROM ybj_insurance WHERE iaccount = #{iaccount}")
    YbjInsurance getInsuranceByAccount(Long account);
    // Read all
    @Select("SELECT * FROM ybj_insurance")
    List<YbjInsurance> getAllInsurance();

    // Update
    @Update("UPDATE ybj_insurance SET iaccount = #{iaccount}, ipremium = #{ipremium}, comid = #{comid} WHERE iid = #{iid}")
    int updateInsurance(YbjInsurance insurance);

    // Delete
}