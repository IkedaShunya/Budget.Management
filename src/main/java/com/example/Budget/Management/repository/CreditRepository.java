package com.example.Budget.Management.repository;

import com.example.Budget.Management.entity.Bank;
import com.example.Budget.Management.entity.Credit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CreditRepository {
    /**
     * 銀行情報の検索
     */
    List<Credit> selectCreditInf(int userId);
    Credit selectBankInfbyCreditId(int userId,int creditcardId);

    /**
     * 銀行情報の登録
     */
    void insertBankInf(Credit credit);

    /**
     * 銀行情報の更新
     */
    void updateBankInf(Credit credit);

    void deleteBankInf(int creditcardId);


}
