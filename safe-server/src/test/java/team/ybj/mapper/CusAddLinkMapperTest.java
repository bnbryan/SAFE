package team.ybj.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.mappers.CusAddLinkMapper;
import team.ybj.pojo.YbjCusAddLink;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional // Ensure that the transaction is rolled back after each test
public class CusAddLinkMapperTest {

    @Autowired
    private CusAddLinkMapper cusAddLinkMapper;

    private YbjCusAddLink testLink;

    @BeforeEach
    public void setUp() {
        // Create a test customer-address link record
        testLink = new YbjCusAddLink(7L, 6L); // Assuming 1L for cid and adid are valid existing IDs in the DB
        // Insert it into the database
        cusAddLinkMapper.insert(testLink);
    }

    @Test
    public void testInsert() {
        // Assuming the insert will be successful if the test setup doesn't throw an exception
        // Further checks can be made if there is feedback from the insert method (like generated ID)
    }

    @Test
    public void testFindAddressIdsByCustomerId() {
        List<Long> addresses = cusAddLinkMapper.findAddressIdsByCustomerId(testLink.getCid());
        assertNotNull(addresses);
        assertFalse(addresses.isEmpty()); // The list should contain at least one address ID
        assertTrue(addresses.contains(testLink.getAdid()));
    }

    @Test
    public void testFindCustomerIdsByAddressId() {
        List<Long> customers = cusAddLinkMapper.findCustomerIdsByAddressId(testLink.getAdid());
        assertNotNull(customers);
        assertFalse(customers.isEmpty()); // The list should contain at least one customer ID
        assertTrue(customers.contains(testLink.getCid()));
    }

    @Test
    public void testDelete() {
        int result = cusAddLinkMapper.delete(testLink);
        assertTrue(result > 0);

        // After deletion, trying to find the link should yield no results
        List<Long> addressesAfterDelete = cusAddLinkMapper.findAddressIdsByCustomerId(testLink.getCid());
        assertFalse(addressesAfterDelete.contains(testLink.getAdid()));
    }
}
