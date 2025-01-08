package com.example.Budget.Management.repository;

import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeCategory;
import com.example.Budget.Management.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 受講生情報を扱うリポジトリ
 *
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

    /**
     * 指定したユーザーの支出カテゴリを検索する
     */
    List<ExpenseCategory> selectexcategoryByuserId(int userId);


}
