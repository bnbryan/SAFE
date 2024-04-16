package team.ybj.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.ybj.mappers.AddressMapper;
import team.ybj.pojo.YbjAddress;


@SpringBootTest
public class AddressMapperTest {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAddressByAdidTest() throws JsonProcessingException {
        YbjAddress actual = addressMapper.getAddressByAdid(1);
        YbjAddress expected = new YbjAddress(1, "123 Main St", "Downtown", "CA", null, "90001");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void insertAddressTest() {
        YbjAddress address = new YbjAddress("111 Adams St", "Brooklyn", "NY", "13A", "11201");
        int success = addressMapper.insertAddress(address);
        Assertions.assertEquals(1, success);
        // restore data
        addressMapper.deleteAddressByAdid(address.getAdid());
    }

    @Test
    public void updateAddressByAdid() {
        YbjAddress address = new YbjAddress(1, "125 Main St", "Downtown", "CA", null, "90001");
        int success = addressMapper.updateAddressByAdid(1, address);
        Assertions.assertEquals(1, success);
        address.setAdstreet("123 Main St");
        int success2;
        // restore data
        do{
            success2 = addressMapper.updateAddressByAdid(1, address);
        } while (success2 != 1);
    }



}
