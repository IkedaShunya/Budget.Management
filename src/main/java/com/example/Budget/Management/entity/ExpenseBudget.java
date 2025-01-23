package com.example.Budget.Management.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExpenseBudget {
    @NotNull
    private int budgetId;
    @NotNull
    private int userId;
    @NotNull
    private int categoryId;
    private int budgetAmount;
    @NotNull
    private int period;
    /** 固定費フラグ*/
    private int isFixedCost;
    /** カテゴリ名*/
    private String categoryName;
    private int year;
    private int month;

    /**0が存在しない　1が存在する*/
    private int dbFlag;

}


