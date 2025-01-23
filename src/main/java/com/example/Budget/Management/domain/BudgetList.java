package com.example.Budget.Management.domain;

import com.example.Budget.Management.entity.ExpenseBudget;
import com.example.Budget.Management.entity.IncomeBudget;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BudgetList {
    private List<IncomeBudget> incomeBudgets;
    private List<ExpenseBudget> expenseBudgets;
}
