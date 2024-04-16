package team.ybj.mappers;

import org.apache.ibatis.annotations.*;
import team.ybj.pojo.YbjCustomer;

import java.util.List;
@Mapper
public interface CustomerMapper {
    // Create
    @Insert("INSERT INTO ybj_customer (clname, cfname, cemail, cpassword, security_question, security_answer, cvalid) " +
            "VALUES (#{clname}, #{cfname}, #{cemail}, #{cpassword}, #{securityQuestion}, #{securityAnswer}, #{cvalid})")
    @Options(useGeneratedKeys = true, keyProperty = "cid")
    int insert(YbjCustomer customer);

    // Read by ID
    @Select("SELECT * FROM ybj_customer WHERE cid = #{cid}")
    YbjCustomer selectByCid(Long cid);

    // Read all
    @Select("SELECT * FROM ybj_customer")
    List<YbjCustomer> selectAll();

    // Update
    @Update("UPDATE ybj_customer SET clname = #{clname}, cfname = #{cfname}, cemail = #{cemail}, " +
            "cpassword = #{cpassword}, security_question = #{securityQuestion}, security_answer = #{securityAnswer}, " +
            "cvalid = #{cvalid} WHERE cid = #{cid}")
    int update(YbjCustomer customer);

    // Delete
    @Delete("DELETE FROM ybj_customer WHERE cid = #{cid}")
    int deleteById(Long cid);
}
