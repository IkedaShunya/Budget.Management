package com.example.Budget.Management.controller;

import com.example.Budget.Management.domain.Category;
import com.example.Budget.Management.entity.ExpenseBudget;
import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeBudget;
import com.example.Budget.Management.entity.IncomeCategory;
import com.example.Budget.Management.service.BudgetService;
import com.example.Budget.Management.service.CategoryService;
import com.example.Budget.Management.utility.SessioninfGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/budget")
public class BudgetController {
    /** DI*/
    private BudgetService service;
    private CategoryService cateservice;
    private SessioninfGet sessioninf;

    @Autowired
    public BudgetController(BudgetService service,CategoryService cateservice,SessioninfGet sessioninf) {
        this.service = service;
        this.cateservice = cateservice;
        this.sessioninf = sessioninf;
    }


    /**
     * メニュー画面
     */
    @GetMapping("/register")
    public String budgetRegister(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            Model model
    ){

        // 現在の年と月を取得
        LocalDate now = LocalDate.now();
        int selectedYear = (year != null) ? year : now.getYear();
        int selectedMonth = (month != null) ? month : now.getMonthValue();


        //LIst＜IncomeCategory＞を作る
        List<IncomeCategory> incomeBudgetList = new ArrayList<>();
        List<ExpenseCategory> expenseBudgetList = new ArrayList<>();
        //categoryテーブルからまずカテゴリ出す
        Category categories = cateservice.searchCategory(sessioninf.getLoginUserId());
        List<IncomeCategory>  incomeCategories = categories.getIncomeCategories();
        List<ExpenseCategory> expenseCategories = categories.getIncomeCategories();

        // →budgetテーブルからcategoryIDと年月で検索してNullではない場合にcategoryテーブルの金額を入れる
        for(IncomeCategory incomeCategory :incomeCategories){
            //categoryIDと年月で検索して金額を出す
            Integer incomeExistAmount = service.searchIncomeAmount(incomeCategory.getCategoryId(),selectedYear,selectedMonth);
            if(incomeExistAmount != null){
                incomeCategory.setEstimatedAmount(incomeExistAmount);
            }

            incomeBudgetList.add(incomeCategory);

        }

        for(ExpenseCategory expenseCategory :expenseCategories){
            //categoryIDと年月で検索して金額を出す
            Integer expenseExistAmount = service.searchExpenseAmount(expenseCategory.getCategoryId(),selectedYear,selectedMonth);

            if(expenseExistAmount != null){
                expenseCategory.setEstimatedAmount(expenseExistAmount);
            }

            expenseBudgetList.add(expenseCategory);
        }
        model.addAttribute("incomeBudgetList", incomeBudgetList);
        model.addAttribute("expenseBudgetList", expenseBudgetList);



    }

}
