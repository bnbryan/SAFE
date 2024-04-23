package team.ybj.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YbjCustomer {
    @JsonProperty("cid")
    private Long cid;
    @JsonProperty("clname")
    private String clname;
    @JsonProperty("cfname")
    private String cfname;
    @JsonProperty("cemail")
    private String cemail;
    @JsonProperty("cpassword")
    private String cpassword;
    @JsonProperty("securityQuestion")
    private String securityQuestion;
    @JsonProperty("answer")
    private String securityAnswer;
    @JsonProperty("valid")
    private String cvalid;
}
