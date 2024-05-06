package team.ybj.mappers;

import org.apache.ibatis.annotations.Param;
import team.ybj.pojo.YbjLoan;

public interface LoanMapper {
    YbjLoan getLoanByAnum(Long anum);

    int insertLoan(YbjLoan loan);

    int updateLrateByAnum(@Param("anum") Long anum, @Param("lrate") Double lrate);

    int updateLamountByAnum(@Param("anum") Long anum, @Param("lamount") Double lamount);

    int updateLvalidByAnum(@Param("anum") Long anum, @Param("lvalid") Character lvalid);

    int updateLpaymentByAnum(@Param("anum") Long anum, @Param("lpayment") Double lpayment);

    int updateLmonthsByAnum(@Param("anum") Long anum, @Param("lmonths") Short lmonths);

    int updateLamountAndLpaymentByAnum(@Param("anum") Long anum, @Param("money") Double money);

    int deleteLoanByAnum(Long anum);
}
