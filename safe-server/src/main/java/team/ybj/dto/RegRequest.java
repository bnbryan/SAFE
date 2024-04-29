package team.ybj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegRequest {

    private String clname;
    private String cfname;
    private String cemail;
    private String cpassword;
    private String securityQuestion;
    private String answer;
    private String valid;
    private String adstreet;
    private String adcity;
    private String adstate;
    private String adapt;
    private String adzip;
}
