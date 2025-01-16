package com.example.Budget.Management.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IncomeBudget {
    @NotNull
    private int budgetId;
    private int userId;
    @NotNull
    private int categoryId;
    /** カテゴリ名*/
    private String categoryName;
    private int budgetAmount;
    private int isRegularIncome;

    private int year;
    private int month;

}


