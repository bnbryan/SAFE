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
    int insertCustomer(YbjCustomer customer);

    // Read by ID

    @Select("SELECT * FROM ybj_customer WHERE cid = #{cid} ")
    YbjCustomer getCustomerByCid(Long cid);
    //现在select的是有效的customer
    @Select("SELECT * FROM ybj_customer WHERE cid = #{cid} AND cvalid = '1' ")
    YbjCustomer getValidCustomerByCid(Long cid);

    @Select("SELECT * FROM ybj_customer WHERE cemail = #{email} AND cvalid = '1' ")
    YbjCustomer getValidCustomerByEmail(String email);

    // Read all
    @Select("SELECT * FROM ybj_customer WHERE cvalid ='1'")
    List<YbjCustomer> getValidAllCustomer();
    @Select("SELECT * FROM ybj_customer")
    List<YbjCustomer> getAllCustomer();

    // Update
    @Update("UPDATE ybj_customer SET clname = #{clname}, cfname = #{cfname}, cemail = #{cemail}, " +
            "cpassword = #{cpassword}, security_question = #{securityQuestion}, security_answer = #{securityAnswer}, " +
            "cvalid = #{cvalid} WHERE cid = #{cid}")
    int updateAll(YbjCustomer customer);

    //不知道改密码的加密要怎么做，姑且摆个这么个东西放着
    @Update("UPDATE ybj_customer SET cpassword = #{password} WHERE cid = #{id}")
    int updatePasswordByCid(@Param("id")Long id,@Param("password") String password);

    // Delete
    // use UpdateValid to imp
    @Update("UPDATE ybj_customer SET cvalid = #{valid} WHERE cid = #{id}")
    int updateValidByCid(@Param("id")Long cid,@Param("valid") String cvalid);

    @Select("SELECT * from ybj_customer WHERE cemail = #{email}")
    YbjCustomer getCustomerByEmail(@Param("email")String email);

    @Select("SELECT LAST_INSERT_ID()")
    Long getLastInsertId();
}
