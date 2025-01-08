package com.example.Budget.Management.Controller;


import com.example.Budget.Management.Service.CategoryService;
import com.example.Budget.Management.domain.Category;
import com.example.Budget.Management.entity.LoginUser;
import com.example.Budget.Management.utility.SessioninfGet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    /** DI*/
    private final CategoryService service;
    private final SessioninfGet sessioninf;

    /**
     * メニュー画面
     */
    @GetMapping("/")
    public String getMenu(){
        return "category/categorymenu";
    }

    /**
     * ユーザー情報一覧をmodelに入れて、Viewを返す
     */
    @GetMapping("/categorylist")
    public String getcategorylist(Model model){
        model.addAttribute("category",service.searchCategory(sessioninf.getLoginUserId()));
        return "category/list";
    }






}
