package com.example.Budget.Management.domain;


import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Category {
    private List<IncomeCategory> incomeCategories;
    private List<ExpenseCategory> expenseCategories;


}
