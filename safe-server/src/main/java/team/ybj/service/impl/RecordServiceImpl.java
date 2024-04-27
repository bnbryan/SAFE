package team.ybj.service.impl;


import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.ybj.dto.ResponseResult;
import team.ybj.exception.*;
import team.ybj.mappers.*;
import team.ybj.pojo.*;
import team.ybj.service.RecordService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {


    @Autowired
    private RecordMapper RecordMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private AccountMapper accountMapper;


    @Override
    public ResponseResult ListRe(YbjCustomer customer) {
        String email = customer.getCemail();
        Long cid = customerMapper.getCustomerByEmail(email).getCid();
        List<YbjAccount> accounts = accountMapper.getAccountsByCid(cid);
        List<YbjRecord> records = new ArrayList<>();
        for(YbjAccount account : accounts) {
            List<YbjRecord> record = new ArrayList<>();
            if(RecordMapper.getRecordByAcc(account.getAnum())!=null){
            record.addAll(RecordMapper.getRecordByAcc(account.getAnum()));
            records.addAll(record);}
        }
        return new ResponseResult(200, "Query success", records);
    }

    @Override
    public int AddRe(Long anum, Long toanum, String ratype, Double ramount) {
         LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime formattedDateTime = LocalDateTime.parse(now.format(formatter));
        YbjRecord record = new YbjRecord(null, anum, toanum, ratype,ramount, formattedDateTime);
        System.out.println(record);
        try{
            int success = RecordMapper.insertRecord(record);
        } catch(Exception e){throw new RuntimeException("Something went wrong when trying to insert record");}
        return 1;
    }
}
