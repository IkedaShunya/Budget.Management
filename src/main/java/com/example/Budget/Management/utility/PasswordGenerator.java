package com.example.Budget.Management.utility;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * ハッシュ化した文字列を返すクラス
 */
public class PasswordGenerator {
    //public static void main(String[] args){
    public static String passwordGenerator(String password){

        //「BCrypt」のインスタンス化
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //入力値
//        String rawPassword = "hana";
        //パスワードをハッシュ化
        String encodedPassword = encoder.encode(password);
        //表示
//        System.out.println("ハッシュ化されたパスワード:" + encodedPassword);
        return encodedPassword;
    }
}
