package team.ybj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApproveAccountRequest {
    private Long appId;
    private Long anum;
    private String aname;
    // *
    private Character type;
    // *
    private Date adate;
    // *
    private Long cid;
    private Long adid;
    // *
    private Double ccharge;
    private Double balance; //default 0
    // *
    private Double srate;
}
