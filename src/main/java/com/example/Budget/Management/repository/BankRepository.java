package com.example.Budget.Management.repository;

import com.example.Budget.Management.entity.Bank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface BankRepository {
    /**
     * 銀行情報の検索
     */
    List<Bank> selectBankInf(int userId);
    Bank selectBankInfbyBankId(int userId,int bankId);


    /**
     * 銀行情報の登録
     */
    void insertBankInf(Bank bank);

    /**
     * 銀行情報の更新
     */
    void updateBankInf(Bank bank);

    void deleteBankInf(int bankId);


}
