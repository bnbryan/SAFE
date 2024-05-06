package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import team.ybj.pojo.YbjAddress;

public interface AddressMapper {

    YbjAddress getAddressByAdid(Long addressId);

    int insertAddress(YbjAddress address);

    int updateAddressByAdid(@Param("adid") Long adid, @Param("address") YbjAddress address);

    int deleteAddressByAdid(Long adid);

    YbjAddress getAddressByCid(Long cid);

    @Select("SELECT LAST_INSERT_ID()")
    Long getLastInsertId();

}
