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
        YbjCustomer customer = customerMapper.getValidCustomerByCid(1L);
        assertNotNull(customer);
        assertEquals("Smith", customer.getClname());
    }
    @Test
    public void testGetCustomerByCid() {
        // 假设数据库中cid为1的用户存在
        Long cid = 1L;
        YbjCustomer customer = customerMapper.getCustomerByCid(cid);
        assertNotNull(customer, "Customer should not be null");
        // 进一步断言，检查customer的其他属性是否符合预期
    }
    @Test
    public void testGetCustomerByEmail(){
        Long cid = 1L;
        YbjCustomer expectCustomer = customerMapper.getCustomerByCid(cid);
        YbjCustomer actualCustomer = customerMapper.getCustomerByEmail(expectCustomer.getCemail());
        assertEquals(expectCustomer,actualCustomer);
        assertEquals(expectCustomer.getCemail(),actualCustomer.getCemail());

    }



    @Test
    public void testGetValidAll() {
        List<YbjCustomer> customers = customerMapper.getValidAllCustomer();
        assertNotNull(customers, "Customers list should not be null");
        assertFalse(customers.isEmpty(), "Customers list should not be empty");
        // 确保列表中的所有用户都是有效的
        customers.forEach(customer ->
                assertEquals("1", customer.getCvalid(), "Customer should be valid")
        );
    }

    @Test
    public void testInsertAndSelectAll() {
        YbjCustomer newCustomer = new YbjCustomer(null, "New", "User", "newuser@example.com", "password", "Question", "Answer", "1");
        customerMapper.insertCustomer(newCustomer);
        assertNotNull(newCustomer.getCid());

        List<YbjCustomer> customers = customerMapper.getAllCustomer();
        assertTrue(customers.size() > 0);
    }

    @Test
    public void testUpdate() {
        YbjCustomer customer = customerMapper.getValidCustomerByCid(1L);
        YbjCustomer updatedCustomer = new YbjCustomer(customer.getCid(), customer.getClname(), customer.getCfname(), "updatedemail@example.com", customer.getCpassword(), customer.getSecurityQuestion(), customer.getSecurityAnswer(), customer.getCvalid());
        customerMapper.updateAll(updatedCustomer);

        YbjCustomer customerAfterUpdate = customerMapper.getValidCustomerByCid(1L);
        assertEquals("updatedemail@example.com", customerAfterUpdate.getCemail());
    }

    @Test
    public void testUpdatePasswordById() {
        Long id = 1L;
        String newPassword = "newPassword123";
        customerMapper.updatePasswordByCid(id, newPassword);
        YbjCustomer customerAfterUpdate = customerMapper.getValidCustomerByCid(1L);
        assertEquals(newPassword,customerAfterUpdate.getCpassword());

    }

    @Test
    public void testUpdateValidById() {
        Long id = 1L;
        String newValid = "0";
        customerMapper.updateValidByCid(id, newValid);
        YbjCustomer customerAfterUpdate = customerMapper.getCustomerByCid(1L);
        assertEquals(newValid,customerAfterUpdate.getCvalid());
    }


}
