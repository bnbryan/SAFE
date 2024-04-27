package team.ybj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YbjRecord {
    private Long rid;
    private Long anum;
    private Long toanum;
    private String ratype;
    private Double ramount;
    private LocalDateTime rtime;


}
