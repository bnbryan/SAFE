package team.ybj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import team.ybj.pojo.YbjAddress;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBasic {
    private Long cid;
    private String clname;
    private String cfname;
    private String cemail;
    private String apt;
    private String street;
    private String city;
    private String state;
    private String zip;

    public CustomerBasic(Long cid, String clname, String cfname, String cemail, YbjAddress address) {
        this.cid = cid;
        this.clname = clname;
        this.cfname = cfname;
        this.cemail = cemail;
        this.street = address.getAdstreet();
        this.city = address.getAdcity();
        this.state = address.getAdstate();
        this.zip = address.getAdzip();
        this.apt = address.getAdapt();
    }
}
