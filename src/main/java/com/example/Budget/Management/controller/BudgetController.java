package com.example.Budget.Management.controller;

import com.example.Budget.Management.domain.BudgetList;
import com.example.Budget.Management.domain.Category;
import com.example.Budget.Management.entity.ExpenseBudget;
import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeCategory;
import com.example.Budget.Management.entity.IncomeBudget;
import com.example.Budget.Management.service.BudgetService;
import com.example.Budget.Management.service.CategoryService;
import com.example.Budget.Management.utility.SessioninfGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    @ModelAttribute
    public BudgetList setUpBudgetListForm() {
        return new BudgetList();
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


        //LIst＜IncomeBudget＞を作る
        List<IncomeBudget> incomeBudgetList = new ArrayList<>();

        List<ExpenseBudget> expenseBudgetList = new ArrayList<>();


        //categoryテーブルからまずカテゴリ出す
        Category categories = cateservice.searchCategory(sessioninf.getLoginUserId());
        List<IncomeCategory>  incomeCategories = categories.getIncomeCategories();
        List<ExpenseCategory> expenseCategories = categories.getExpenseCategories();

        // →budgetテーブルからcategoryIDと年月で検索してNullではない場合にcategoryテーブルの金額を入れる
        for(IncomeCategory incomeCategory :incomeCategories){
            IncomeBudget incomeBudget = new IncomeBudget();
            incomeBudget.setUserId(incomeCategory.getUserId());
            incomeBudget.setCategoryId(incomeCategory.getCategoryId());
            incomeBudget.setCategoryName(incomeCategory.getCategoryName());
            incomeBudget.setIsRegularIncome(incomeCategory.getIsRegularIncome());
            incomeBudget.setYear(selectedYear);
            incomeBudget.setMonth(selectedMonth);

            //categoryIDと年月で検索して金額を出す
            Integer incomeExistAmount = service.searchIncomeAmount(incomeCategory.getCategoryId(),selectedYear,selectedMonth);
            if(incomeExistAmount != null){
//                incomeBudget.setFormattedBudgetAmount(NumberFormat.getNumberInstance(Locale.JAPAN).format(incomeExistAmount));
                incomeBudget.setBudgetAmount(incomeExistAmount);

            }else{
//                incomeBudget.setFormattedBudgetAmount(NumberFormat.getNumberInstance(Locale.JAPAN).format(incomeCategory.getBudgetAmount()));
                incomeBudget.setBudgetAmount(incomeCategory.getEstimatedAmount());
            }

            incomeBudgetList.add(incomeBudget);

        }

        for(ExpenseCategory expenseCategory :expenseCategories){
            ExpenseBudget expenseBudget = new ExpenseBudget();
            expenseBudget.setUserId(expenseCategory.getUserId());
            expenseBudget.setCategoryId(expenseCategory.getCategoryId());
            expenseBudget.setCategoryName(expenseCategory.getCategoryName());
            expenseBudget.setIsFixedCost(expenseCategory.getIsFixedCost());
            expenseBudget.setYear(selectedYear);
            expenseBudget.setMonth(selectedMonth);


            //categoryIDと年月で検索して金額を出す
            Integer expenseExistAmount = service.searchExpenseAmount(expenseCategory.getCategoryId(),selectedYear,selectedMonth);

            if(expenseExistAmount != null){
//                expenseBudget.setFormattedBudgetAmount(NumberFormat.getNumberInstance(Locale.JAPAN).format(expenseExistAmount));
                expenseBudget.setBudgetAmount(expenseExistAmount);

            }else{
//                expenseBudget.setFormattedBudgetAmount(NumberFormat.getNumberInstance(Locale.JAPAN).format(expenseCategory.getEstimatedAmount()));
                expenseBudget.setBudgetAmount(expenseCategory.getEstimatedAmount());
            }
            expenseBudgetList.add(expenseBudget);
        }


//        model.addAttribute("incomeBudgetList", incomeBudgetList);
//        model.addAttribute("expenseBudgetList", expenseBudgetList);
        BudgetList budgetList = new BudgetList();
        budgetList.setIncomeBudgets(incomeBudgetList);
        budgetList.setExpenseBudgets(expenseBudgetList);

        model.addAttribute(budgetList);

        return "budget/register";

    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Integer.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    // カンマを削除してIntegerに変換
                    setValue(Integer.parseInt(text.replace(",", "")));
                }
            }

            @Override
            public String getAsText() {
                Object value = getValue();
                if (value == null) {
                    return "";
                } else {
                    // カンマ区切りでフォーマット
                    return String.format("%,d", (Integer) value);
                }
            }
        });
    }

}
