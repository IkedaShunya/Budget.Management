package com.example.Budget.Management.controller;


import com.example.Budget.Management.entity.Bank;
import com.example.Budget.Management.entity.Credit;
import com.example.Budget.Management.service.BankService;
import com.example.Budget.Management.service.CreditService;
import com.example.Budget.Management.utility.SessioninfGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/credit")
public class CreditController {
    /**
     * DI
     */
    private CreditService service;
    private BankService bankService;
    private SessioninfGet sessioninf;

    @Autowired
    public CreditController(CreditService service, BankService bankService, SessioninfGet sessioninf) {
        this.service = service;
        this.bankService = bankService;
        this.sessioninf = sessioninf;

    }

    @ModelAttribute
    public Credit setUpCreditFrom() {
        return new Credit();
    }

    @ModelAttribute
    public Bank setUpBankFrom() {
        return new Bank();
    }

    /**
     * メニュー画面
     */
    @GetMapping("")
    public String getMenu() {
        return "credit/menu";
    }

    /**
     * Creditリストの表示
     */
    @GetMapping("/list")
    public String getCreditInflist(Model model) {
        List<Credit> creditList = service.searchCredit();
        model.addAttribute("creditList", creditList);
        return "credit/list";
    }

    /**
     * Creditの削除
     */
    @GetMapping("/delete")
    public String deleteCredit(@RequestParam("id") String creditcardId,
                               Model model, RedirectAttributes redirectAttributes) {
        service.deleteCreditInf(Integer.parseInt(creditcardId));
        redirectAttributes.addFlashAttribute("message", "削除が完了しました");
        return "redirect:/credit/list";

    }


    /**
     * Creditの編集
     */
    @GetMapping("/edit-credit")
    public String creditinfDisplay(@RequestParam("id") int creditId, Model model) {

        Credit credit = service.searchCreditInfByCreditId(creditId);
        model.addAttribute("credit", credit);
        if (credit.getTransferDate() == -1) {
            model.addAttribute("isLastDay", true); // 初期状態は「末日チェックあり」
        } else {
            model.addAttribute("isLastDay", false); // 初期状態は「末日チェックなし」
        }


        List<Bank> bankList = bankService.searchBankInf(sessioninf.getLoginUserId());
        model.addAttribute("bankList", bankList);

        return "credit/edit";

    }

    @PostMapping("/edit-credit/execute")
    public String editCredit(@ModelAttribute Credit credit,
                             @RequestParam(name = "isLastDay", defaultValue = "false") boolean isLastDay,
                             BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        //末払い-1の時の処理
        // 末日がチェックされていたら day を -1 に設定
        if (isLastDay) {
            credit.setTransferDate(-1);
        }

        service.updateCreditInf(credit);
        redirectAttributes.addFlashAttribute("message", "編集が完了しました");
        //mapping(java側にリダイレクトしている)
        return "redirect:/credit/list";
    }


    /**
     * Creditの登録
     */
    @GetMapping("/insert")
    public String insertDisplay(Model model) {
        List<Bank> bankList = bankService.searchBankInf(sessioninf.getLoginUserId());
        model.addAttribute("bankList", bankList);
        return "credit/insert";
    }

    @PostMapping("/insert/execute")
    public String insertCredit(@ModelAttribute Credit credit
            , @RequestParam(name = "isLastDay", defaultValue = "false") boolean isLastDay
            , BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        //末払い-1の時の処理
        if (isLastDay) {
            credit.setTransferDate(-1);
        }
        service.insertCreditInf(credit);
        redirectAttributes.addFlashAttribute("message", "登録が完了しました");
        //mapping(java側にリダイレクトしている)
        return "redirect:/credit/list";
    }


}
