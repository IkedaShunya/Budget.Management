package com.example.Budget.Management.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /** ユーザー名*/
    private String userName;
    /**パスワード*/
    private String password;
    /** 権限*/
    private Role authority;
    /**表示名*/
    private String displayName;
    /**　emailアドレス*/
    private String email;
    /**ID*/
    private int userId;
    /**削除フラグ*/
    private Integer deleteFlag;


}
