package team.ybj.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class YbjAccount {
    private Long anum;
    private String aname;
    private Date adate;
    private Character atype;
    private Long cid;
    private Long adid;

    public YbjAccount() {
    }

    public YbjAccount(Long anum, String aname, Date adate, Character atype, Long cid, Long adid) {
        this.anum = anum;
        this.aname = aname;
        this.adate = adate;
        this.atype = atype;
        this.cid = cid;
        this.adid = adid;
    }

    public YbjAccount(String aname, Date adate, Character atype, Long cid, Long adid) {
        this.aname = aname;
        this.adate = adate;
        this.atype = atype;
        this.cid = cid;
        this.adid = adid;
    }
}
