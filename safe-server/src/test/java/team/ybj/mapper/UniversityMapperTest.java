package team.ybj.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.ybj.mappers.UniversityMapper;
import team.ybj.mappers.LoanMapper;
import team.ybj.pojo.YbjUniversity;
import team.ybj.pojo.YbjLoan;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


@SpringBootTest
public class UniversityMapperTest {

    @Autowired
    UniversityMapper universityMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    LoanMapper loanMapper;

    @Test
    public void getUniversityByUidTest() throws JsonProcessingException {
        YbjUniversity actual = universityMapper.getUniversityByUid(1L);
        YbjUniversity expected = new YbjUniversity(1L, "State University");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void insertUniversityTest() {

        YbjUniversity university = new YbjUniversity(10L, "State2 University");
        int success = universityMapper.insertUniversity(university);
        Assertions.assertEquals(1, success);
        // restore data
        universityMapper.deleteUniversityByUid(university.getUID());
    }

}
