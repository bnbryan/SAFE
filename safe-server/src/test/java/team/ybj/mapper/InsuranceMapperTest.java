package team.ybj.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.mappers.InsuranceMapper;
import team.ybj.pojo.YbjInsurance;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional // Ensures that the transaction is rolled back after each test
public class InsuranceMapperTest {

    @Autowired
    private InsuranceMapper insuranceMapper;

    private YbjInsurance testInsurance;

    @BeforeEach
    public void setUp() {
        // Create a test insurance record
        testInsurance = new YbjInsurance(null, 123456789L, new BigDecimal("100.00"), 1L);
        // Insert it into the database
        insuranceMapper.insert(testInsurance);
    }

    @Test
    public void testInsert() {
        assertNotNull(testInsurance.getIid()); // After insertion, iid should be auto-generated and not null
    }

    @Test
    public void testSelectByLid() {
        YbjInsurance found = insuranceMapper.selectByLid(testInsurance.getIid());
        assertNotNull(found);
        assertEquals(testInsurance.getIpremium(), found.getIpremium());
    }

    @Test
    public void testSelectAll() {
        List<YbjInsurance> allInsurances = insuranceMapper.selectAll();
        assertFalse(allInsurances.isEmpty()); // The list should not be empty after insertion
    }

    @Test
    public void testUpdate() {
        // Change some details of the insurance
        YbjInsurance updated = new YbjInsurance(testInsurance.getIid(), 987654321L, new BigDecimal("200.00"), 2L);
        int result = insuranceMapper.update(updated);
        assertTrue(result > 0);

        // Fetch the updated record and validate changes
        YbjInsurance updatedInsurance = insuranceMapper.selectByLid(updated.getIid());
        assertNotNull(updatedInsurance);
        assertEquals(updated.getIpremium(), updatedInsurance.getIpremium());
    }

    @Test
    public void testDeleteByLid() {
        int result = insuranceMapper.deleteByLid(testInsurance.getIid());
        assertTrue(result > 0);

        // Trying to fetch the deleted record should yield null
        YbjInsurance deletedInsurance = insuranceMapper.selectByLid(testInsurance.getIid());
        assertNull(deletedInsurance);
    }
}
