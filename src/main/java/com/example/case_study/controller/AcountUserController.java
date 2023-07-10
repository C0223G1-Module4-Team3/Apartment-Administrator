package com.example.case_study.controller;

import com.example.case_study.model.Employee;
import com.example.case_study.service.employee.IEmployeeService;
import com.example.case_study.untils.WebUltils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/account")
public class AcountUserController {
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("")
    public String accountList(Principal principal,Model model){
        model.addAttribute("account",this.employeeService.displayListEmployeeHaveAccount());
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        return "account";
    }
}
