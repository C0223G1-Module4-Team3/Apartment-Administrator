package com.example.case_study.controller;

import com.example.case_study.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AcountUserController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("")
    public String accountList(Model model){
        model.addAttribute("account",this.employeeService.displayListEmployeeHaveAccount());
        return "account";
    }
}
