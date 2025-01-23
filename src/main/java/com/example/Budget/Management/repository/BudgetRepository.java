package com.example.Budget.Management.repository;

import com.example.Budget.Management.entity.ExpenseBudget;
import com.example.Budget.Management.entity.IncomeBudget;
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


    /**
     * 登録ボタン押下後の処理
     */
    void insertIncomeBudget(IncomeBudget incomeBudget);
    void updateIncomeBudget(IncomeBudget incomeBudget);

    void insertExpenseBudget(ExpenseBudget expenseBudget);
    void updateExpenseBudget(ExpenseBudget expenseBudget);

    /**
     * 不足分(削除分)のカテゴリを追加
     */
    List<IncomeBudget> selectLackincomeBudgetList(List<Integer> incomecateIdList,int selectedYear,int selectedMonth,int userid );
    List<ExpenseBudget> selectLackexpenseBudgetList(List<Integer> expensecateIdList,int selectedYear,int selectedMonth,int userid);

}
