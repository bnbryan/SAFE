package team.ybj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    Long fromAccountNum;
    Character fromAccountType;
    Long toAccountNum;
    Double amount;
}
