package com.example.case_study.controller;

import com.example.case_study.dto.AccountUserDto;
import com.example.case_study.model.AccountUser;
import com.example.case_study.model.Employee;
import com.example.case_study.repository.IFacilityRepository;
import com.example.case_study.service.IAccountService;
import com.example.case_study.service.IContractService;
import com.example.case_study.service.customer.ICustomerService;
import com.example.case_study.service.employee.IEmployeeService;
import com.example.case_study.service.facility.IFacilityService;
import com.example.case_study.service.room.IRoomService;
import com.example.case_study.untils.WebUltils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class Login {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private IFacilityService facilityService;

    @GetMapping("/login")
    public String formLogin(@RequestParam(value = "error", required = false) boolean error, Model model) {
        if (error) {
            model.addAttribute("msg", "Incorrect password or phone number");
            return "login";
        }
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!"anonymousUser".equals(authentication)) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @GetMapping("/logoutSuccessful")
    public String logout(Model model) {
        return "login";
    }

    @GetMapping("/dashboard")
    public String userInfo(Model model, Principal principal) {
        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("employee", this.employeeService.displayEmployeeHome());
        model.addAttribute("customer", this.customerService.displayListCustomer());
        model.addAttribute("contract", this.contractService.findContractHome());
        model.addAttribute("room", this.roomService.findAll());
        model.addAttribute("maleCustomer", this.customerService.showMaleCustomer());
        model.addAttribute("femaleCustomer", this.customerService.showFemaleCustomer());
        model.addAttribute("maleEmployee", this.employeeService.showMaleEmployee());
        model.addAttribute("femaleEmployee", this.employeeService.showFemaleEmployee());
        model.addAttribute("contractTrue", this.contractService.findSuccessContract());
        model.addAttribute("contractFail", this.contractService.findNotSuccessContract());
        model.addAttribute("maintenance",this.roomService.findRoomByMaintenance());
        model.addAttribute("facility",this.facilityService.display());
        return "home";
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
