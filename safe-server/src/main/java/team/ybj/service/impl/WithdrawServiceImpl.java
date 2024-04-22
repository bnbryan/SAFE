package team.ybj.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.ybj.dto.ResponseResult;
import team.ybj.exception.*;
import team.ybj.mappers.*;
import team.ybj.pojo.*;
import team.ybj.service.*;


@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private CheckingMapper checkingMapper;
    @Autowired
    private SavingMapper savingMapper;

    @Override
    public ResponseResult Withdraw(YbjChecking checking) {
        Long anum = checking.getAnum();
        YbjChecking Checking = checkingMapper.getCheckingByAnum(anum);
        YbjSaving Saving = savingMapper.getSavingByAnum(anum);
        if(Checking == null&&Saving == null) {throw new ServiceException("Something went wrong when withdrawing");}
        if(Checking == null) {
            Double balance = Saving.getSbalance();
            Double newBalance = balance - checking.getAbalance();
            if(newBalance<0) {
                throw new LackBalanceException("Balance not enough");
            }
            try{
                savingMapper.updateSbalanceByAnum(anum, newBalance);
            } catch(Exception e){throw new ServiceException("Something went wrong when withdrawing");}
            return new ResponseResult(200, "withdraw success", newBalance);
        }
        Double balance = Checking.getAbalance();
        Double newBalance = balance - checking.getAbalance();
        if(newBalance<0) {
            throw new LackBalanceException("Balance not enough");
        }
        try{
            checkingMapper.updateAbalanceByAnum(anum, newBalance);
        } catch(Exception e){throw new ServiceException("Something went wrong when withdrawing");}
        return new ResponseResult(200, "withdraw success", newBalance);
    }

}
