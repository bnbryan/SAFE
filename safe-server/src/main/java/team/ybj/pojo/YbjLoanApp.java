package team.ybj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YbjLoanApp {
    private Long cid;
    private Double lrate;
    private Double lamount;
    private Short lmonths;
    private Double lpayment;
    private String ltype;
    private Date hyear;
    private Double hinsurance;
    private Long laiaccount;
    private String lacomname;
    private Double ipremium;
    private String stuid;
    private Character stutype;
    private Date stugraddate;
    private Character lvalid;

}