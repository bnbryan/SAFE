package team.ybj.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class YbjUniversity {
    private Long uID;
    private String uname;

    public YbjUniversity() {
    }

    public YbjUniversity(Long uID, String uname) {
        this.uID = uID;
        this.uname = uname;
    }

    public YbjUniversity(String uname) {
        this.uname = uname;
    }
}
