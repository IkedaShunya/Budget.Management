package com.example.Budget.Management.utility;

import com.example.Budget.Management.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * セッション情報の取得を行う
 */

@Component
public class SessioninfGet {
    /**　ログインユーザーのIDを取得*/
    public int getLoginUserId() {
        //SecurityContextは「現在のセッションに関連付けられた認証情報」を保持しているオブジェクトです。
        //Authenticationには、ログインしたユーザーに関する情報（ユーザー名、権限、パスワードなど）が含まれています。
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //authentication.getPrincipal():　認証されたユーザーの主な情報を取得します。
        //instanceof LoginUser　LoginUserオブジェクトが指定されたクラス、
        // またはそのサブクラスのインスタンスであるか（LoginUserはUserクラスを継承している）
        //authenticationはUserクラスを継承しているか確認している
//        if (authentication != null && authentication.getPrincipal() instanceof LoginUser) {
//            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//            return loginUser.getUserId(); // userIdを取得
//        }
//        return ; // 認証されていない場合
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return loginUser.getUserId();
    }

}