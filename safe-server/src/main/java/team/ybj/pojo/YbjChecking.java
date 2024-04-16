package team.ybj.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class YbjChecking {
    private Long anum;
    private Double ccharge;
    private Character atype;
    private Double abalance;
    private Character cvalid;

    public YbjChecking() {
    }

    public YbjChecking(Long anum, Double ccharge, Character atype, Double abalance, Character cvalid) {
        this.anum = anum;
        this.ccharge = ccharge;
        this.atype = atype;
        this.abalance = abalance;
        this.cvalid = cvalid;
    }

    public YbjChecking(Double ccharge, Character atype, Double abalance, Character cvalid) {
        this.ccharge = ccharge;
        this.atype = atype;
        this.abalance = abalance;
        this.cvalid = cvalid;
    }
}
