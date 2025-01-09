package com.example.Budget.Management.Controller;


import com.example.Budget.Management.Service.CategoryService;
import com.example.Budget.Management.domain.Category;
import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeCategory;
import com.example.Budget.Management.entity.LoginUser;
import com.example.Budget.Management.repository.CategoryRepository;
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
@RequestMapping("/category")
public class CategoryController {
    /** DI*/
    private CategoryService service;
    private SessioninfGet sessioninf;

    @Autowired
    public CategoryController(CategoryService service,SessioninfGet sessioninf) {
        this.service = service;
        this.sessioninf = sessioninf;
    }

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
    @GetMapping("/list")
    public String getcategorylist(Model model){
        Category categories = service.searchCategory(sessioninf.getLoginUserId());
        model.addAttribute("incomeCategories",categories.getIncomeCategories());
        model.addAttribute("expenseCategories",categories.getExpenseCategories());

        return "category/categorylist";
    }






}
