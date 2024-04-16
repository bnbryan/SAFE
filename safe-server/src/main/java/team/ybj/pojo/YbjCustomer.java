package team.ybj.pojo;

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
    private String securityAnswer;
    private String cvalid;
}
