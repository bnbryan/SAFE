package team.ybj.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@Repository
public class YbjAccount {
    private Integer anum;
    private String aname;
    private Date adate;
    private Character atype;
    private Integer cid;
    private Integer adid;

    public YbjAccount() {
    }

    public YbjAccount(Integer anum, String aname, Date adate, Character atype, Integer cid, Integer adid) {
        this.anum = anum;
        this.aname = aname;
        this.adate = adate;
        this.atype = atype;
        this.cid = cid;
        this.adid = adid;
    }
}
