package com.example.Budget.Management.Service;

import com.example.Budget.Management.domain.Category;
import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeCategory;
import com.example.Budget.Management.entity.User;
import com.example.Budget.Management.repository.CategoryRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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



}

