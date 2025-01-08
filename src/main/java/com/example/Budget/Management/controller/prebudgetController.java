package com.example.Budget.Management.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 事前予算入力用
 *
 */
@Controller
@RequestMapping("/prebudget")
public class prebudgetController {
    @GetMapping("/input")
    public String inputdisplay(){



        return "projectedexpend/preinput";
    }


}
