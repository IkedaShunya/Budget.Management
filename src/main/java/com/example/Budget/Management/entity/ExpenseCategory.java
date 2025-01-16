package com.example.Budget.Management.entity;

import lombok.Data;

/**
 * expense_category(支出カテゴリを表すクラス)
 */
@Data
public class ExpenseCategory {
    /** カテゴリID*/
    private int categoryId;
    /** ユーザーID*/
    private int userId;
    /** カテゴリ名*/
    private String categoryName;
    /** 固定費フラグ*/
    private int isFixedCost;
    /** 予測収入金額*/
    private int estimatedAmount;





}
