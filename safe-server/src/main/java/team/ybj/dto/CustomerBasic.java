package team.ybj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBasic {
    private Long cid;
    private String clname;
    private String cfname;
    private String cemail;
}
