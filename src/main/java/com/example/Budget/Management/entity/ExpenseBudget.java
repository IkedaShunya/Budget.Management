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

}


