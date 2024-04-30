package team.ybj.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class YbjCustomer {
    private Long cid;
    private String clname;
    private String cfname;
    private String cemail;
    private String cpassword;
    private String securityQuestion;
    @JsonProperty("answer")
    private String securityAnswer;
    @JsonProperty("valid")
    private String cvalid;
}
