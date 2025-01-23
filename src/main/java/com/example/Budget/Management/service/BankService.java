package com.example.Budget.Management.service;

import com.example.Budget.Management.entity.Bank;
import com.example.Budget.Management.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private BankRepository repository;

    @Autowired
    public BankService(BankRepository repository){
        this.repository = repository;
    }

    /**
     *ユーザーIDから銀行情報を検索する
     */
    public List<Bank> searchBankInf(int userId){
        return repository.selectBankInf(userId);
    }

    public Bank searchBankInfByBankId(int userId,int bankId){
        return repository.selectBankInfbyBankId(userId, bankId);
    }

    public void insertBankInf(Bank bankinf){
        repository.insertBankInf(bankinf);
    }

    public void updateBankInf(Bank bankinf){
        repository.updateBankInf(bankinf);
    }

    public void deleteBankinfByid(int bankId){
        repository.deleteBankInf(bankId);
    }



}
