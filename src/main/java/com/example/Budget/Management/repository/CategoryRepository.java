package com.example.Budget.Management.repository;

import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeCategory;
import com.example.Budget.Management.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * 受講生情報を扱うリポジトリ
 * 全件、条件検索、コース検索が行えるクラス
 */

//擬似的なデータベースみたいなもの
//@MapperがつくとMyBatisが管理するということを自動的に認識してくれる
@Mapper
public interface CategoryRepository {
    /**
     * 指定したユーザーの収入カテゴリを検索する
     */
    List<IncomeCategory>  selectincategoryByuserId(int userId);

    List<IncomeCategory>  selectincategoryByDay(int userId, LocalDate specifiedDate);

    /**
     * 指定したユーザーの支出カテゴリを検索する
     */
    List<ExpenseCategory> selectexcategoryByuserId(int userId);
    List<ExpenseCategory> selectexcategoryByDay(int userId, LocalDate specifiedDate);

    /**
     * 指定したカテゴリーIDを検索する
     */
    ExpenseCategory selectexcategoryCategoryId(int categoryId);


    /**
     * 収入カテゴリの編集
     */
    void updateExpensecategoryinf(ExpenseCategory expensecategory);

    /**
     * 指定したカテゴリーIDを検索する
     */
    IncomeCategory selectincategoryCategoryId(int categoryId);


    /**
     * 収入カテゴリの編集
     */
    void updateIncomecategoryinf(IncomeCategory incomeCategory);

    /**
     *収入カテゴリの削除
     */
    void deleteIncomeCategory(int categoryId);

    /**
     *支出カテゴリの削除
     */
    void deleteExpenseCategory(int categoryId);

    /**
     *収入カテゴリの作成
     */
    void insertIncomeCategory(IncomeCategory incomeCategory);

    /**
     *支出カテゴリの作成
     */
    void insertExpenseCategory(ExpenseCategory expensecategory);

}
