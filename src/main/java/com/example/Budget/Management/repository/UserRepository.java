package com.example.Budget.Management.repository;

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
public interface UserRepository {
    /**
     * 全件検索をする
     */
    List<User> selectByUsers();
    List<User> selectByAllUsers();
    /**
     * 指定したユーザーIDのユーザー情報を検索する
     */
    User selectuserByid(int id);

    /**
     * 指定したユーザー名のユーザー情報を検索する
     */
    User selectuserByuserName(String userName);
    /**
     * 指定したユーザー名のユーザー情報を検索する
     */
    User selectuserByemail(String email);

    /**
     * 指定されたユーザーを削除する
     */
    void softDeleteByUser(int id);

    /**
     * ユーザー情報の更新をする
     */
    void updateUserinf(User user);


    /**
     * パスワード以外のユーザー情報を更新する
     */
    void updateNopass(User user);

    /**
     * ユーザー情報を登録する
     */
    void insertUserinf(User user);


}
