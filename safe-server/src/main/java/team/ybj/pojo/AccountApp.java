package team.ybj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountApp {
    private Long appId;
    private Long cid;
    private Character type;
    private Double income;
    private String career;
    private Character status;

    public AccountApp(Long cid, Character type, Double income, String career) {
        this.cid = cid;
        this.type = type;
        this.income = income;
        this.career = career;
    }

}
