package team.ybj.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.mybatis.AutoConfigureMybatis;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.ybj.mappers.AddressMapper;
import team.ybj.mappers.CompanyMapper;
import team.ybj.pojo.YbjAddress;
import team.ybj.pojo.YbjCompany;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 保证每个测试方法之后进行事务回滚
public class CompanyMapperTest {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private AddressMapper addressMapper;

    @BeforeEach
    void setUp() {
        // 可以在这里插入一些测试数据，如果不是使用内存数据库
    }

    @Test
    void testInsert() {
        YbjAddress test = new YbjAddress("asda","asdasd","asdadasd","a","123");
        addressMapper.insertAddress(test);
        YbjCompany company = new YbjCompany(null, "TestCompany", test.getAdid());
        int result = companyMapper.insertCompany(company);
        assertTrue(result > 0);
        assertNotNull(company.getComid()); // 确保 id 被自动设置
    }

    @Test
    void testSelectById() {
        YbjCompany company = companyMapper.getCompanyById(1L);
        assertNotNull(company);
        assertEquals("SecureInsure", company.getComname());
    }

    @Test
    void testSelectAll() {
        List<YbjCompany> companies = companyMapper.getAllCompany();
        assertFalse(companies.isEmpty());
        assertEquals(10, companies.size()); // 假设初始数据有10条记录
    }

    @Test
    void testUpdate() {
        YbjCompany company = new YbjCompany(1L, "UpdatedName", 3L);
        int result = companyMapper.updateCompany(company);
        assertTrue(result > 0);
        YbjCompany updatedCompany = companyMapper.getCompanyById(1L);
        assertEquals("UpdatedName", updatedCompany.getComname());
    }

}
