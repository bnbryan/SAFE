package team.ybj.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class YbjAddress {
    private Long adid;
    private String adstreet;
    private String adcity;
    private String adstate;
    private String adapt;
    private String adzip;

    public YbjAddress() {
    }

    public YbjAddress(Long adid, String adstreet, String adcity, String adstate, String adapt, String adzip) {
        this.adid = adid;
        this.adstreet = adstreet;
        this.adcity = adcity;
        this.adstate = adstate;
        this.adapt = adapt;
        this.adzip = adzip;
    }

    public YbjAddress(String adstreet, String adcity, String adstate, String adapt, String adzip) {
        this.adstreet = adstreet;
        this.adcity = adcity;
        this.adstate = adstate;
        this.adapt = adapt;
        this.adzip = adzip;
    }
}
