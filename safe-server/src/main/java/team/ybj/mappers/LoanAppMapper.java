package team.ybj.mappers;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import team.ybj.pojo.AccountApp;
import team.ybj.pojo.YbjLoanApp;

import java.util.List;

@Repository
public interface LoanAppMapper {
    @Insert("INSERT INTO ybj_loan_app (cid, lrate, lamount, lmonths, lpayment, ltype, hyear, hinsurance, laiaccount, " +
            "lacomname, ipremium,stuid, stutype, stugraddate, uname, lavalid)" + "VALUES (#{cid}, #{lrate}, #{lamount}, " +
            "#{lmonths}, #{lpayment}, #{ltype}, #{hyear}, #{hinsurance}, #{laiaccount}, #{lacomname}, #{ipremium}, " +
            " #{stuid}, #{stutype}, #{stugraddate},#{uname}, #{lavalid})")
    @Options(useGeneratedKeys = true, keyProperty = "laid")
    int insertLoanApp(YbjLoanApp loanApp);

    @Delete("DELETE FROM ybj_loan_app WHERE laid=#{Laid}")
    int deleteLoanAppById(Long Laid);

    @Select("SELECT * FROM ybj_loan_app WHERE cid=#{cid}")
    List<YbjLoanApp> findLoanAppsByCid(Long cid);

    @Select("SELECT * FROM ybj_loan_app WHERE laid=#{laid}")
    YbjLoanApp findLoanAppByLaid(Long laid);

    @Update("UPDATE ybj_loan_app SET lavalid=#{lavalid} WHERE laid=#{laid}")
    int updateLoanAppStatus(Long laid, Character lavalid);
}
