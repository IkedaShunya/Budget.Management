package com.example.Budget.Management.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
//springsecurityが提供するUserDetailsインターフェイスを簡単実装したクラス
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * ユーザーの認証情報を表すUserDetails実装クラス
 */

public class LoginUser extends User {

    //[追加部分]追加のフィールド

    private String displayName;
    private String email;
    private int userId;

    /** 最低限の情報を保持したUserDetails
     * 実装クラスUserを作成する
     */
    public LoginUser(String userName, String password,
                    /*ユーザーに割り当てられた権限のリスト。
                    権限はユーザーが実行可能な操作やアクセス可能なリソースを定義するために使用される。
                    つまり認可で使用されます。*/
                     Collection<? extends GrantedAuthority> authorities,
                     int userId,String displayName,String email){
       //userクラスのコンストラクタ
        super(userName, password, authorities);
        this.userId = userId;
        this.displayName = displayName;
        this.email = email;
    }

    //[追加部分]diplaynameのgetter
    public int getUserId(){
        return userId;
    }
    public String getDisplayName(){
        return displayName;
    }
    public String getEmail(){
        return email;
    }
}
