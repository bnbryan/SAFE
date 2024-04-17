package team.ybj.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class YbjSaving {
    private Long anum;
    private Double srate;
    private Character atype;
    private Double sbalance;
    private Character svalid;

    public YbjSaving() {
    }

    public YbjSaving(Long anum, Double srate, Character atype, Double sbalance, Character svalid) {
        this.anum = anum;
        this.srate = srate;
        this.atype = atype;
        this.sbalance = sbalance;
        this.svalid = svalid;
    }

    public YbjSaving(Double srate, Character atype, Double sbalance, Character svalid) {
        this.srate = srate;
        this.atype = atype;
        this.sbalance = sbalance;
        this.svalid = svalid;
    }
}
