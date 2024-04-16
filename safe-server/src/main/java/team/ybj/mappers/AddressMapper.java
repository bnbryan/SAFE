package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import team.ybj.pojo.YbjAddress;

public interface AddressMapper {

    YbjAddress getAddressByAdid(Integer addressId);

    int insertAddress(YbjAddress address);

    int updateAddressByAdid(@Param("adid") Integer adid, @Param("address") YbjAddress address);

    int deleteAddressByAdid(Integer adid);

}
