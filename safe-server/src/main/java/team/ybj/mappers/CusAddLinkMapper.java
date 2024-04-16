package team.ybj.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import team.ybj.pojo.YbjCusAddLink;

import java.util.List;
@Mapper
public interface CusAddLinkMapper {
    // Create a link
    @Insert("INSERT INTO ybj_cust_addr (cid, adid) VALUES (#{cid}, #{adid})")
    int insert(YbjCusAddLink link);

    // Find all addresses for a customer
    @Select("SELECT adid FROM ybj_cust_addr WHERE cid = #{cid}")
    List<Long> findAddressIdsByCustomerId(Long cid);

    // Find all customers for an address
    @Select("SELECT cid FROM ybj_cust_addr WHERE adid = #{adid}")
    List<Long> findCustomerIdsByAddressId(Long adid);

    // Delete a link
    @Delete("DELETE FROM ybj_cust_addr WHERE cid = #{cid} AND adid = #{adid}")
    int delete(YbjCusAddLink link);
}
