package com.example.Budget.Management.entity;



import lombok.Data;

/**
 * expense_category(支出カテゴリを表すクラス)
 */

@Data
public class IncomeCategory {
    /** カテゴリID*/
    private int categoryId;
    /** ユーザーID*/
    private int userId;
    /** カテゴリ名*/
    private String categoryName;
    /** 予測収入金額*/
    private int estimatedAmount;


}
