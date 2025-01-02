package com.example.Budget.Management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        //エンコードの設定
        /*NoOperation(何もしない)のパスワードエンコーダーを使用しており、
        これによりパスワードは変換されずにそのままの形で保存される*/
        //return NoOpPasswordEncoder.getInstance();

        //パスワード安全にハッシュ化するために使用されるクラスの一つです
        return new BCryptPasswordEncoder();
    }
}
