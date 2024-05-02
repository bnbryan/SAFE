package team.ybj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetAppsResponse {
    private Long appId;
    private Character type;
    private Character status;

    public UserGetAppsResponse(Long laid, String ltype, Character lvalid) {
    }
}
