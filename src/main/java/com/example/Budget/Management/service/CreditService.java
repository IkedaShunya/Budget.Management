package com.example.Budget.Management.service;

import com.example.Budget.Management.repository.BankRepository;
import com.example.Budget.Management.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {
    private CreditRepository repository;
    private BankRepository bankRepository;

    @Autowired
    public CreditService(CreditRepository repository, BankRepository bankRepository){
        this.repository = repository;
        this.bankRepository = bankRepository;

    }

    /**
     * ユーザー情報の検索をする
     */

}
