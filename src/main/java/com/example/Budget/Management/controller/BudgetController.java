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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

        //DBで開始日がきていないものは除外するための変数
        LocalDate specifiedDate = LocalDate.of(selectedYear, selectedMonth, 01);


        //categoryテーブルからまずカテゴリ出す
        Category categories = cateservice.searchCategoryByDay(sessioninf.getLoginUserId(),specifiedDate);
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
                incomeBudget.setDbFlag(1);

            }else{
//                incomeBudget.setFormattedBudgetAmount(NumberFormat.getNumberInstance(Locale.JAPAN).format(incomeCategory.getBudgetAmount()));
                incomeBudget.setBudgetAmount(incomeCategory.getEstimatedAmount());
                incomeBudget.setDbFlag(0);
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
                expenseBudget.setDbFlag(1);

            }else{
//                expenseBudget.setFormattedBudgetAmount(NumberFormat.getNumberInstance(Locale.JAPAN).format(expenseCategory.getEstimatedAmount()));
                expenseBudget.setBudgetAmount(expenseCategory.getEstimatedAmount());
                expenseBudget.setDbFlag(0);
            }
            expenseBudgetList.add(expenseBudget);
        }

        //もしcategoryテーブルには削除されているが、
        //予算テーブルにあるカテゴリをIncomeBudgetsとExpenseBudgetsに追加する
        List<Integer> incomecateIdList = incomeBudgetList.stream()
                .map(IncomeBudget::getCategoryId)
                .collect(Collectors.toList());

        List<Integer> expensecateIdList = expenseBudgetList.stream()
                .map(ExpenseBudget::getCategoryId)
                .collect(Collectors.toList());

        List<IncomeBudget> lackIncomeBudgetList =service.lackincomeBudgetList(incomecateIdList,selectedYear,selectedMonth,sessioninf.getLoginUserId());
        List<ExpenseBudget> lackExpenseBudgetList =service.lackExpenseBudgetList(expensecateIdList,selectedYear,selectedMonth,sessioninf.getLoginUserId());

        incomeBudgetList.addAll(lackIncomeBudgetList);
        expenseBudgetList.addAll(lackExpenseBudgetList);


//        model.addAttribute("incomeBudgetList", incomeBudgetList);
//        model.addAttribute("expenseBudgetList", expenseBudgetList);
        BudgetList budgetList = new BudgetList();
        budgetList.setIncomeBudgets(incomeBudgetList);
        budgetList.setExpenseBudgets(expenseBudgetList);

        // ドロップダウン用の年と月リスト生成
        List<Integer> years = IntStream.rangeClosed(now.getYear() - 10, now.getYear()+1).boxed().toList();
        List<Integer> months = IntStream.rangeClosed(1, 12).boxed().toList();

        model.addAttribute(budgetList);
        model.addAttribute("years", years);
        model.addAttribute("months", months);
        model.addAttribute("selectedYear", selectedYear);
        model.addAttribute("selectedMonth", selectedMonth);

        return "budget/register";

    }

    @PostMapping("/insert/execute")
    public String insert(@ModelAttribute BudgetList budgetList
            , BindingResult result, Model model, RedirectAttributes redirectAttributes){
        //budgetListからinputデータを出力
        List<IncomeBudget> inputincomeBudgets = budgetList.getIncomeBudgets();
        List<ExpenseBudget> inputexpenseBudgets = budgetList.getExpenseBudgets();
        service.insertBudget(inputincomeBudgets ,inputexpenseBudgets);
        int year = inputincomeBudgets.get(0).getYear();
        int month = inputincomeBudgets.get(0).getMonth();


        redirectAttributes.addFlashAttribute("message", "編集が完了しました");
        return "redirect:/budget/register?year=" + year + "&month=" + month;
        }

//        service.updateExpenseCategoryinf(expensecategory);
//        redirectAttributes.addFlashAttribute("message", "編集が完了しました");
//        //mapping(java側にリダイレクトしている)
//        return "redirect:/category/list";
  //  }







}
