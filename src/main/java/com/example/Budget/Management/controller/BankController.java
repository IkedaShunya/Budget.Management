package com.example.Budget.Management.controller;

import com.example.Budget.Management.entity.Bank;
import com.example.Budget.Management.entity.ExpenseCategory;
import com.example.Budget.Management.service.BankService;
import com.example.Budget.Management.service.BudgetService;
import com.example.Budget.Management.utility.SessioninfGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.YearMonth;
import java.util.List;

@Controller
@RequestMapping("/bank")
public class BankController {
    /**DI*/
    private BankService service;
    private SessioninfGet sessioninf;

    @Autowired
    public BankController(BankService service,SessioninfGet sessioninf){
        this.service = service;
        this.sessioninf = sessioninf;
    }
    @ModelAttribute
    public Bank setUpBankFrom(){
        return new Bank();
    }

    /**
     * メニュー画面
     */
    @GetMapping("")
    public String getMenu(){
        return "bank/bankmenu";
    }

    @GetMapping("/list")
    public String getBankInflist(Model model){

        List<Bank> bankList = service.searchBankInf(sessioninf.getLoginUserId());
        model.addAttribute("bankList",bankList);
        return "bank/list";
    }

    @GetMapping("/edit-bankinf")
    public String bankinfDisplay(@RequestParam("id") int bankId, Model model){
        Bank bankinf = service.searchBankInfByBankId(sessioninf.getLoginUserId(),bankId);
        model.addAttribute("bankinf",bankinf);
        return "bank/edit";

    }
    @PostMapping("/edit-bankinf/execute")
    public String editBank(@ModelAttribute Bank bankinf
            , BindingResult result, Model model, RedirectAttributes redirectAttributes){
        service.updateBankInf(bankinf);
        redirectAttributes.addFlashAttribute("message", "編集が完了しました");
        //mapping(java側にリダイレクトしている)
        return "redirect:/bank/list";
    }

    @GetMapping("/insert")
    public String insertDisplay(Model model){
        List<Bank> bankList = service.searchBankInf(sessioninf.getLoginUserId());
        model.addAttribute("bankList",bankList);
        return "bank/insertBankInf";
    }

    @PostMapping("/insert/execute")
    public String insertBank(@ModelAttribute Bank bankinf
            , BindingResult result, Model model, RedirectAttributes redirectAttributes){
        bankinf.setUserId(sessioninf.getLoginUserId());
        service.insertBankInf(bankinf);
        redirectAttributes.addFlashAttribute("message", "登録が完了しました");
        //mapping(java側にリダイレクトしている)
        return "redirect:/bank/list";
    }

    /**
     * ViewからIDを受け取り、そのID番号のユーザー情報を取得し、画面に変えす（削除確認）
     */
    @GetMapping("/delete")
    public String deleteBank(@RequestParam("id") String bankId,
                                Model model, RedirectAttributes redirectAttributes){
        service.deleteBankinfByid(Integer.parseInt(bankId));
        redirectAttributes.addFlashAttribute("message", "削除が完了しました");
        return "redirect:/bank/list";

    }

}
