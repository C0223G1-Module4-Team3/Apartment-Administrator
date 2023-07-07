package com.example.case_study.controller;

import com.example.case_study.service.IAccountService;
import com.example.case_study.untils.WebUltils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class Login {
    @Autowired
    private IAccountService accountService;

    @GetMapping("")
    public String formLogin(Model model) {
        return "login";
    }

   @GetMapping("/dashboard")
    public String userInfo(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
       String userName = principal.getName();

       System.out.println("User Name: " + userName);

       User loginedUser = (User) ((Authentication) principal).getPrincipal();

       String userInfo = WebUltils.toString(loginedUser);
       model.addAttribute("userInfo", userInfo);
        return "layout";
    }

    @GetMapping("/400")
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUltils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }

}
