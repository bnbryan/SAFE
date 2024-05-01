package team.ybj.pojo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YbjInsurance {
    private Long iid;
    private Long iaccount;
    private BigDecimal ipremium;
    private Long comid;
}
