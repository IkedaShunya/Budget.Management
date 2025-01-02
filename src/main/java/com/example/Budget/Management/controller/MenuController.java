package com.example.Budget.Management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Menu：コントローラー
 */

@Controller
@RequestMapping("/")
public class MenuController {

    /**
     * メニュー画面を表示する
     */
    @GetMapping
    public String showMenu(){
        return "menu";
    }


    /**
     * テスト画面を表示する
     */
    @GetMapping("test")
    public String showTest(){
        return "test";
    }
}
