package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import team.ybj.pojo.YbjAddress;

public interface AddressMapper {

    YbjAddress getAddressByAdid(Long addressId);

    int insertAddress(YbjAddress address);

    int updateAddressByAdid(@Param("adid") Long adid, @Param("address") YbjAddress address);

    int deleteAddressByAdid(Long adid);

}
