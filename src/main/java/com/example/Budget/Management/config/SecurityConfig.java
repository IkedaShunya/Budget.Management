package com.example.Budget.Management.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//このクラスがSpringSecurityの設定クラス
@Configuration
//springSecurityを有効化し、Webセキュリティの設定をカスタマイズする
@EnableWebSecurity
//finalがついたフィールドのみコンストラクタを作成できる（コンストラクタインジェクションされる）
@RequiredArgsConstructor
public class SecurityConfig{

    /** DI対象が存在すれば、DIを使用する*/
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    //sevurityFilterChainのBean定義

    //そのメソッドがBeanを返すことを示します。
    //このBeanはDIコンテナによって管理されます
    @Bean
    //SecurityFilterChainはWebリクエストがサーバーに到達する際に一連のセキュリティチェックを行います
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //★HTTPリクエストに対するセキュリティ設定
                //どのHTTPリクエストに認証が必要かを定義する。
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                        //「login」へのアクセスは認証を必要としない（誰でもアクセス可）
                        .requestMatchers("/login").permitAll()
                        //[管理者権限設定]url:/test/**は管理者しかアクセスできない
                        .requestMatchers("/test/**","/usermanage/**").hasAnyAuthority("ADMIN")
                        //その他のリクエストは認証が必要
                        .anyRequest().authenticated())
                //フォームベースのログイン設定
                //フォームベース認証(username/password をフォームで入力する方式)の設定です。
                .formLogin(form -> form
                        //カスタマイズログインページのURLを指定
                        .loginPage("/login")
                        //ログイン処理のURLを指定
                        .loginProcessingUrl("/authentication")
                        //ユーザー名,passwordのname属性を指定
                        .usernameParameter("usernameInput")
                        .passwordParameter("passwordInput")
                        //ログイン成功時のリダイレクト先を指定
                        .defaultSuccessUrl("/")
                        //ログイン失敗時のリダイレクト先を指定
                        .failureUrl("/login?error"))
                //ログアウト設定
                .logout(logout -> logout
                        //ログアウトを処理するURLを指定
                        .logoutUrl("/logout")
                        //ログアウト成功時のリダイレクト先を指定
                        .logoutSuccessUrl("/login?logout")
                        //ログアウト時にセッションを無効にする
                        .invalidateHttpSession(true)
                        //ログアウト時にCookieを削除する
                        .deleteCookies("JSESSIONID"));



        //セキュリティ設定を構築し、最終的なSecurityFilterChainオブジェクトを返す
        return http.build();
    }


}
