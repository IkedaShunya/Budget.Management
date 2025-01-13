package com.example.Budget.Management.service;

import com.example.Budget.Management.domain.Category;
import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeCategory;
import com.example.Budget.Management.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ユーザー情報管理に関するサービスクラス
 */

@Service
public class CategoryService {
    /** DI*/
    private CategoryRepository repository;


    @Autowired
    public CategoryService(CategoryRepository repository) {

        this.repository = repository;
    }


    /**
     * カテゴリ一覧の検索
     * @return　List<User>　カテゴリ情報の一覧
     */
    public Category searchCategory(int userId){

        List<IncomeCategory> incomeCategories = repository.selectincategoryByuserId(userId);
        List<ExpenseCategory> expenseCategories = repository.selectexcategoryByuserId(userId);

        Category category = new Category(incomeCategories,expenseCategories);

        return category;
    }

    /**
     * 支出カテゴリの検索
     * @return　List<User>　カテゴリ情報の一覧
     */
    public ExpenseCategory searchexCategorybyCategoryId(int categoryId){

        ExpenseCategory expenseCategory = repository.selectexcategoryCategoryId(categoryId);

        return expenseCategory;
    }

    /**
     *支出カテゴリの編集
     */
    public void updateExpenseCategoryinf(ExpenseCategory expensecategory){
        repository.updateExpensecategoryinf(expensecategory);
    }

    /**
     * 収入カテゴリの検索
     * @return　List<User>　カテゴリ情報の一覧
     */
    public IncomeCategory searchinCategorybyCategoryId(int categoryId){

        IncomeCategory incomeCategory = repository.selectincategoryCategoryId(categoryId);

        return incomeCategory;
    }

    /**
     *収入カテゴリの編集
     */
    public void updateIncomeCategoryinf(IncomeCategory incomeCategory){
        repository.updateIncomecategoryinf(incomeCategory);
    }

    /**
     *収入カテゴリの削除
     */
    public void deleteIncomeCategory(int categoryId){
        repository.deleteIncomeCategory(categoryId);
    }
    /**
     *支出カテゴリの削除
     */
    public void deleteExpenseCategory(int categoryId){
        repository.deleteExpenseCategory(categoryId);
    }

    /**
     * 収入カテゴリの削除
     */
    public void insertIncomeCategory(IncomeCategory incomeCategory){
        repository.insertIncomeCategory(incomeCategory);
    }
    /**
     * 支出カテゴリの削除
     */
    public void insertExpenseCategory(ExpenseCategory expensecategory){
        repository.insertExpenseCategory(expensecategory);
    }



}

