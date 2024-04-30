package team.ybj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.ybj.pojo.YbjAccount;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetail {
    private Long anum;
    private String aname;
    private Date date;
    private Character atype;
    private Long cid;
    private Long adid;
    private Double balance;
    private Double rate;
    private Double charge;
    private String loanType;

    public AccountDetail(YbjAccount account, Double balance, Double rate, Double charge, String loanType) {
        this.anum = account.getAnum();
        this.aname = account.getAname();
        this.date = account.getAdate();
        this.atype = account.getAtype();
        this.cid = account.getCid();
        this.adid = account.getAdid();
        this.balance = balance;
        this.rate = rate;
        this.charge = charge;
        this.loanType = loanType;
    }

}
