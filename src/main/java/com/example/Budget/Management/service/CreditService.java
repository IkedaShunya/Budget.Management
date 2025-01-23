package com.example.Budget.Management.service;

import com.example.Budget.Management.entity.Bank;
import com.example.Budget.Management.entity.Credit;
import com.example.Budget.Management.repository.BankRepository;
import com.example.Budget.Management.repository.CreditRepository;
import com.example.Budget.Management.utility.SessioninfGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    private CreditRepository repository;
    private SessioninfGet sessioninf;

    @Autowired
    public CreditService(CreditRepository repository,SessioninfGet sessioninf){
        this.repository = repository;
        this.sessioninf = sessioninf;
    }

    /**
     * ユーザー情報から検索をする
     */
    public List<Credit> searchCredit(){
        return repository.selectCreditInf(sessioninf.getLoginUserId());
    }

    public Credit searchCreditInfByCreditId(int creditcardId){
        return repository.selectBankInfbyCreditId(sessioninf.getLoginUserId(), creditcardId);
    }

    /**
     *Credit情報の挿入
     */
    public void insertCreditInf(Credit credit){
        credit.setUserId(sessioninf.getLoginUserId());
        repository.insertCreditInf(credit);
    }

    /**
     *Credit情報の更新
     */
    //
    public void updateCreditInf(Credit credit){
        repository.updateCreditInf(credit);
    }

    /**
     *Credit情報の削除
     */
    public void deleteCreditInf(int creditcardId){

        repository.deleteCreditInf(creditcardId);
    }




}
