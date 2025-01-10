package com.example.Budget.Management.Controller;


import com.example.Budget.Management.Service.CategoryService;
import com.example.Budget.Management.domain.Category;
import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.entity.IncomeCategory;
import com.example.Budget.Management.utility.SessioninfGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @GetMapping("")
    public String getMenu(){
        return "category/categorymenu";
    }

    /**
     * カテゴリー一覧をmodelに入れて、Viewを返す
     */
    @GetMapping("/list")
    public String getcategorylist(Model model){
        Category categories = service.searchCategory(sessioninf.getLoginUserId());
        model.addAttribute("incomeCategories",categories.getIncomeCategories());
        model.addAttribute("expenseCategories",categories.getExpenseCategories());

        return "category/categorylist";
    }

    /**
     *カテゴリー情報（支出）の編集
     */
    @GetMapping("/edit-expenseCategory")
    public String editCategory(@RequestParam("id") int categoryId, Model model) {
        ExpenseCategory expensecategory = service.searchexCategorybyCategoryId(categoryId);

        model.addAttribute("expenseCategory",expensecategory);
        return "category/editexcate";
        //IDチェック
    }

    @PostMapping("/edit/execute-expenseCategory")
    public String updateExpensecategory(@ModelAttribute ExpenseCategory expensecategory
            , BindingResult result, Model model, RedirectAttributes redirectAttributes){

        service.updateExpenseCategoryinf(expensecategory);
        redirectAttributes.addFlashAttribute("message", "編集が完了しました");
        //mapping(java側にリダイレクトしている)
        return "redirect:/category/list";
    }

    /**
     *カテゴリー情報（収入）の編集
     */
    @GetMapping("/edit-incomeCategory")
    public String editinCategory(@RequestParam("id") int categoryId, Model model) {
        IncomeCategory incomeCategory = service.searchinCategorybyCategoryId(categoryId);

        model.addAttribute("incomeCategory",incomeCategory);
        return "category/editincate";
        //IDチェック
    }

    @PostMapping("/edit/execute-incomeCategory")
    public String updateIncomecategory(@ModelAttribute IncomeCategory incomeCategory
            , BindingResult result, Model model, RedirectAttributes redirectAttributes){

        service.updateIncomeCategoryinf(incomeCategory);
        redirectAttributes.addFlashAttribute("message", "編集が完了しました");
        //mapping(java側にリダイレクトしている)
        return "redirect:/category/list";
    }

    @GetMapping("/delete-incomeCategory")
    public String deleteinCategory(@RequestParam("id") int categoryId,  RedirectAttributes redirectAttributes) {
        service.deleteIncomeCategory(categoryId);

        redirectAttributes.addFlashAttribute("message", "削除が完了しました");
        return "redirect:/category/list";
        //IDチェック
    }

    @GetMapping("/delete-expenseCategory")
    public String deleteexCategory(@RequestParam("id") int categoryId,  RedirectAttributes redirectAttributes) {
        service.deleteExpenseCategory(categoryId);

        redirectAttributes.addFlashAttribute("message", "削除が完了しました");
        return "redirect:/category/list";
        //IDチェック
    }



}
