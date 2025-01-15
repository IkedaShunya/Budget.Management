package com.example.Budget.Management.service;


import com.example.Budget.Management.domain.Category;
import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeCategory;
import com.example.Budget.Management.repository.BudgetRepository;
import com.example.Budget.Management.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {
    /** DI*/
    private BudgetRepository repository;
    @Autowired
    public BudgetService(BudgetRepository repository) {
        this.repository = repository;
    }

    /**budgetテーブルからcategoryIDと年月で検索して金額を出力
     * categoryId
     * @param categoryId
     * @param selectedYear
     * @param selectedMonth
     * @return
     */
    public Integer searchIncomeAmount(int categoryId,int selectedYear,int selectedMonth){
        Integer incomeExistAmount= repository.selectIncomeAmount(categoryId, selectedYear,selectedMonth);
        return incomeExistAmount;
    }

    public Integer searchExpenseAmount(int categoryId,int selectedYear,int selectedMonth){
        Integer expenseExistAmount= repository.selectExpenseAmount(categoryId, selectedYear,selectedMonth);
        return expenseExistAmount;
    }



}
