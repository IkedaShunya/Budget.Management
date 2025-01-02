package com.example.Budget.Management.repository;


import com.example.Budget.Management.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthenticationRepository {
    /**
     * ユーザー認証の検索
     */
    User selectByUsername(String  username);
}
