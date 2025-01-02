package com.example.Budget.Management.service;

import com.example.Budget.Management.entity.User;
import com.example.Budget.Management.repository.UserRepository;
import com.example.Budget.Management.utility.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ユーザー情報管理に関するサービスクラス
 */

@Service
@RequiredArgsConstructor
public class UserMangementService {

    /** DI*/
    private final UserRepository repository;

    /**
     * ユーザ情報の一覧を検索
     * @return　List<User>　ユーザー情報の一覧
     */
    public List<User> searchUserinf(){

        return repository.selectByUsers();

    }

    public List<User> searchAllUserinf(){

        return repository.selectByAllUsers();

    }

    /**
     * ユーザ情報の一覧を検索
     * @return　List<User>　ユーザー情報の一覧
     */

    public User searchUserinfByid(int id) {
        return repository.selectuserByid(id);
    }


    /**
     * ユーザーIDを受け取りそのユーザーを論理削除します
     */
    public void deleteUser(int id){
        repository.softDeleteByUser(id);
    }


    /**
     * ユーザー情報を更新する
     */
    public void updateUserinf(User user){
        if(user.getPassword()==null){
            repository.updateNopass(user);
        }else{
            String encodedPassword = PasswordGenerator.passwordGenerator(user.getPassword());
            user.setPassword(encodedPassword);
            repository.updateUserinf(user);
        }

    }

    /**
     * ユーザー情報を登録する
     */
    public void insertUsrinf(User user){
        String encodedPassword = PasswordGenerator.passwordGenerator(user.getPassword());
        user.setPassword(encodedPassword);
        repository.insertUserinf(user);
    }


}

