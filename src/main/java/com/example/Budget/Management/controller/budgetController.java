//package com.example.Budget.Management.controller;
//
//import com.example.Budget.Management.service.CategoryService;
//import com.example.Budget.Management.utility.SessioninfGet;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.time.LocalDate;
//
//@Controller
//@RequestMapping("/budget")
//public class budgetController {
//    /** DI*/
//    private BudegetService service;
//    private CategoryService cateservice;
//
//    @Autowired
//    public CategoryController(BudegetService service) {
//        this.service = service;
//    }
//    @Autowired
//    public CategoryController(BudegetService service) {
//        this.service = service;
//    }
//
//    /**
//     * メニュー画面
//     */
//    @GetMapping("/register")
//    public String budgetRegister(
//            @RequestParam(name = "year", required = false) Integer year,
//            @RequestParam(name = "month", required = false) Integer month,
//            Model model
//    ){
//        // 現在の年と月を取得
//        LocalDate now = LocalDate.now();
//        int selectedYear = (year != null) ? year : now.getYear();
//        int selectedMonth = (month != null) ? month : now.getMonthValue();
//
//        = service.searchCategory(sessioninf.getLoginUserId());
//    }
//
//}
