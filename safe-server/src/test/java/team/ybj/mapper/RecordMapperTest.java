package team.ybj.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.ybj.mappers.RecordMapper;
import team.ybj.pojo.YbjRecord;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class RecordMapperTest {

    @Autowired
    RecordMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getRecordByRidTest() throws JsonProcessingException {
        YbjRecord record =  mapper.getRecordByRid(1L);
        String expectedJson = "{\"rid\":1,\"anum\":1,\"toanum\":2,\"ratype\":\"C\",\"ramount\":1500.0,\"rtime\":\"2023-09-01T14:30:00\"}";

        String actualJson = objectMapper.writeValueAsString(record);
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    public void getRecordByAccTest() throws JsonProcessingException {
        List<YbjRecord> actual = mapper.getRecordByAcc(3L);
        LocalDateTime ldt = LocalDateTime.of(2023, Month.SEPTEMBER, 2, 10, 00, 00);
        LocalDateTime ldt2 = LocalDateTime.of(2023, Month.SEPTEMBER, 3, 9, 45, 00);

        YbjRecord re1 = new YbjRecord(2L, 3L, 4L, "L", 200.00, ldt);
        YbjRecord re2 = new YbjRecord(3L, 3L, null, "L",320.50, ldt2);

        List<YbjRecord> expected = Arrays.asList(re1, re2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void insertRecordTest() {
        Random random = new Random();
        Long rid = random.nextLong();
        LocalDateTime ldt = LocalDateTime.of(2023, Month.SEPTEMBER, 2, 10, 00, 00);
        YbjRecord record = new YbjRecord(rid, 4L, 10L, "S",5000.00, ldt);
        int success = mapper.insertRecord(record);
        Assertions.assertEquals(1, success);
        // restore data
        mapper.deleteRecordByRid(rid);
    }

}
