package com.example.Budget.Management.service;

import com.example.Budget.Management.Service.CategoryService;
import com.example.Budget.Management.entity.User;
import com.example.Budget.Management.entity.LoginUser;
import com.example.Budget.Management.entity.Role;
import com.example.Budget.Management.repository.AuthenticationRepository;
import com.example.Budget.Management.utility.SessioninfGet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * カスタム認証サービス
 */
@Service
public class LoginUserDetailsServiceImpl implements UserDetailsService {


    /** DI*/
    private AuthenticationRepository authenticationRepository;
    @Autowired
    public LoginUserDetailsServiceImpl(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException{
        //「認証テーブル」からデータを取得
        User user = authenticationRepository.selectByUsername(username);

        // 論理削除されているかをチェック
        if (user.getDeleteFlag()==1) {
            throw new UsernameNotFoundException("ユーザーが削除されています: " + username);
        }

        //対象データがあれば、UserDetailsの実装クラスを返す
        if(user != null){
            //対象データが存在する
            //UserDetailsの実装クラスを返す
            return new LoginUser(user.getUserName(),
                    user.getPassword(),
                    //getAuthorityListメソッドは特定のユーザーが持つ権限をリスト形式で返す
                    getAuthorityList(user.getAuthority()),
                    user.getUserId(),
                    user.getDisplayName(),
                    user.getEmail());
        }else{
            //対象データが存在しない
            throw new UsernameNotFoundException(
                    username + "=> 指定しているユーザー名は存在しません" );
        }

    }

    /**
     * 権限情報をリストで取得する
     */
    private List<GrantedAuthority> getAuthorityList(Role role){
        //権限リスト　GrantedAuthorityはspringsecurityが提供する「権限」表すために使用されるインターフェイス
        List<GrantedAuthority> authorities = new ArrayList<>();
        //列挙型からロールを取得　SimpleGrantedAuthorityはspringsecurityが提供するGrantedAuthorityインターフェイスを簡易実装したクラス
        authorities.add(new SimpleGrantedAuthority(role.name()));
        //ADMIN ロールの場合、USERの権限も付与
        if(role == Role.ADMIN){
            authorities.add(
                    new SimpleGrantedAuthority(Role.USER.toString()));
        }
        return  authorities;
    }


}
