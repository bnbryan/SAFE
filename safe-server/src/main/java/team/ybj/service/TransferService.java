package team.ybj.service;

import team.ybj.pojo.TransferRequest;

public interface TransferService {

    int transfer(TransferRequest transferRequest);

    int transferFromChecking(Long fromAccountNum, Long toAccountNum, Double amount);

    int transferFromSaving(Long fromAccountNum, Long toAccountNum, Double amount);




}
