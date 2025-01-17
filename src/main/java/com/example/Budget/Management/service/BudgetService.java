package com.example.Budget.Management.service;


import com.example.Budget.Management.domain.Category;
import com.example.Budget.Management.entity.ExpenseBudget;
import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeBudget;
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

    public void insertBudget(List<IncomeBudget> inputincomeBudgets, List<ExpenseBudget> inputexpenseBudgets){
        for(IncomeBudget incomeBudget : inputincomeBudgets){
            if(incomeBudget.getDbFlag() == 0){
                repository.insertIncomeBudget(incomeBudget);
            }else if(incomeBudget.getDbFlag() == 1){
                repository.updateIncomeBudget(incomeBudget);
            }
        }

        for(ExpenseBudget expenseBudget : inputexpenseBudgets){
            if(expenseBudget.getDbFlag() == 0){
                repository.insertExpenseBudget(expenseBudget);
            }else if(expenseBudget.getDbFlag() == 1){
                repository.updateExpenseBudget(expenseBudget);
            }
        }
    }

    public List<IncomeBudget> lackincomeBudgetList(List<Integer> incomecateIdList,int selectedYear,int selectedMonth, int id){
        List<IncomeBudget> lackIncomeBudgetList =repository.selectLackincomeBudgetList(incomecateIdList,selectedYear,selectedMonth,id);
        if(lackIncomeBudgetList != null){
            for(int i =0; i < lackIncomeBudgetList.size(); i++){
                lackIncomeBudgetList.get(i).setDbFlag(1);
            }
        }

        return lackIncomeBudgetList;
    }
    public List<ExpenseBudget> lackExpenseBudgetList(List<Integer> expensecateIdList,int selectedYear,int selectedMonth, int id){
        List<ExpenseBudget> lackExpenseBudgetList =repository.selectLackexpenseBudgetList(expensecateIdList,selectedYear,selectedMonth,id);
        if(lackExpenseBudgetList != null){
            for(int i =0; i < lackExpenseBudgetList.size(); i++){
                lackExpenseBudgetList.get(i).setDbFlag(1);
            }
        }
        return lackExpenseBudgetList;

    }





}
