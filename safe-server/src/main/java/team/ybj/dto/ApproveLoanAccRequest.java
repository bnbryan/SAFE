package team.ybj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveLoanAccRequest {
    // *
    private Long laid;
    private Double lrate;
    private Double lpayment;
}
