package com.example.Budget.Management.repository;

import com.example.Budget.Management.entity.IncomeCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BudgetRepository {
    /**
     * 指定したategoryIDと年月で検索して金額を出力
     */
    Integer selectIncomeAmount(int categoryId,int selectedYear,int selectedMonth);

    Integer selectExpenseAmount(int categoryId,int selectedYear,int selectedMonth);

}
