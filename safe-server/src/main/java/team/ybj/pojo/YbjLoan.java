package team.ybj.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class YbjLoan {
    private Long anum;
    private Double lrate;
    private Double lamount;
    private Short lmonths;
    private Double lpayment;
    private String ltype;
    private Date hyear;
    private Double hinsurance;
    private Long iid;
    private String stuid;
    private Character stutype;
    private Date stugraddate;
    private Long uID;
    private Character atype;
    private Character lvalid;

    public YbjLoan(){
    }

    public YbjLoan(Long anum, Double lrate, Double lamount, Short lmonths, Double lpayment, String ltype, Date hyear,
                    Double hinsurance, Long iid, String stuid, Character stutype, Date stugraddate, Long uID, Character atype,
                    Character lvalid) {
        this.anum = anum;
        this.lrate = lrate;
        this.lamount = lamount;
        this.lmonths = lmonths;
        this.lpayment = lpayment;
        this.ltype = ltype;
        this.hyear = hyear;
        this.hinsurance = hinsurance;
        this.iid = iid;
        this.stuid = stuid;
        this.stutype = stutype;
        this.stugraddate = stugraddate;
        this.uID = uID;
        this.atype = atype;
        this.lvalid = lvalid;
    }

    public YbjLoan(Double lrate, Double lamount, Short lmonths, Double lpayment, String ltype, Date hyear,
                    Double hinsurance, Long iid, String stuid, Character stutype, Date stugraddate, Long uID, Character atype,
                    Character lvalid) {
        this.lrate = lrate;
        this.lamount = lamount;
        this.lmonths = lmonths;
        this.lpayment = lpayment;
        this.ltype = ltype;
        this.hyear = hyear;
        this.hinsurance = hinsurance;
        this.iid = iid;
        this.stuid = stuid;
        this.stutype = stutype;
        this.stugraddate = stugraddate;
        this.uID = uID;
        this.atype = atype;
        this.lvalid = lvalid;
    }
}
