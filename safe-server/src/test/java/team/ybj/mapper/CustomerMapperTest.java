package team.ybj.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.mappers.CustomerMapper;
import team.ybj.pojo.YbjCustomer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void testSelectByCid() {
        YbjCustomer customer = customerMapper.selectByCid(1L);
        assertNotNull(customer);
        assertEquals("Smith", customer.getClname());
    }

    @Test
    public void testInsertAndSelectAll() {
        YbjCustomer newCustomer = new YbjCustomer(null, "New", "User", "newuser@example.com", "password", "Question", "Answer", "1");
        customerMapper.insert(newCustomer);
        assertNotNull(newCustomer.getCid());

        List<YbjCustomer> customers = customerMapper.selectAll();
        assertTrue(customers.size() > 0);
    }

    @Test
    public void testUpdate() {
        YbjCustomer customer = customerMapper.selectByCid(1L);
        YbjCustomer updatedCustomer = new YbjCustomer(customer.getCid(), customer.getClname(), customer.getCfname(), "updatedemail@example.com", customer.getCpassword(), customer.getSecurityQuestion(), customer.getSecurityAnswer(), customer.getCvalid());
        customerMapper.update(updatedCustomer);

        YbjCustomer customerAfterUpdate = customerMapper.selectByCid(1L);
        assertEquals("updatedemail@example.com", customerAfterUpdate.getCemail());
    }

}
