package com.example.case_study.controller;

import com.example.case_study.dto.AccountUserDto;
import com.example.case_study.dto.EmployeeDto;
import com.example.case_study.model.AccountUser;
import com.example.case_study.model.Employee;
import com.example.case_study.model.RoleUser;
import com.example.case_study.service.IAccountService;
import com.example.case_study.service.employee.IEmployeeService;
import com.example.case_study.service.employee.IRoleUserService;
import com.example.case_study.untils.WebUltils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AcountUserController {
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IRoleUserService roleUserService;

    @Autowired
    private IAccountService accountService;

    @GetMapping("")
    public String accountList(Principal principal,Model model){
        model.addAttribute("account",this.employeeService.displayListEmployeeHaveAccount());
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        return "/account/account";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model, Principal principal) {
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        Employee employee1 = employeeService.getEmployeeById(id);
        if (employee1 == null) {
            model.addAttribute("msg", "ID not found.  ");
            return "account/account";
        } else {
            model.addAttribute("employee1", employee1);
            return "account/accountDetails";
        }
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("employeeDto",new EmployeeDto());
        model.addAttribute("ruleList",roleUserService.findAll());
        model.addAttribute("accountList",accountService.findAll());
        return "account/create";
    }

    @PostMapping("create")
    public String createNewEmployee(@Valid @ModelAttribute EmployeeDto employeeDto,
                                    @Valid @ModelAttribute AccountUserDto accountUserDto,
                                    BindingResult bindingResult, Model model,
                                    RedirectAttributes redirectAttributes) {
        new AccountUserDto().validate(accountUserDto,bindingResult);
        new EmployeeDto().validate(employeeDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("ruleList",roleUserService.findAll());
//            model.addAttribute("employeeDto",employeeDto);
            return "account/create";
        }
        Employee employee = new Employee();
        AccountUser accountUser = new AccountUser();
        String hashedPassword = BCrypt.hashpw(employeeDto.getPassword(),BCrypt.gensalt());
        accountUserDto.setPhoneNumber(employeeDto.getPhoneNumber());
        accountUserDto.setPassword(hashedPassword);
        accountUserDto.setRule(employeeDto.getAccountUser().getRoleUser());
        BeanUtils.copyProperties(employeeDto, employee);
        BeanUtils.copyProperties(accountUserDto,accountUser);
        accountService.createAccount(accountUser);
        accountUser.setRoleUser(employeeDto.getAccountUser().getRoleUser());
        employeeService.createEmployee(employee);
        redirectAttributes.addFlashAttribute("message","Create finish");
        return "redirect:/account";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id,RedirectAttributes redirectAttributes){
        employeeService.deleteEmployee(id);
        if (employeeService.deleteAccount(id)){
            redirectAttributes.addFlashAttribute("message", "Xoá Thành công");
        }else {
            redirectAttributes.addFlashAttribute("message", "Không tìm thấy id");
        }
        return "redirect:/account";
    }

    @GetMapping("/account/{option}")
    public String optionStatus(@PathVariable("option") String option) {
        System.out.println(option);
        String[] arrStr = option.split(",");
        Employee employee = employeeService.getEmployeeById(Integer.parseInt(arrStr[0]));
        AccountUser accountUsers = accountService.getAccountById(Integer.parseInt(arrStr[0]));
        employee.getAccountUser().getRoleUser().setId(Integer.parseInt(arrStr[1]));
        employeeService.editEmployee(employee);
        accountUsers.getRoleUser().setId(Integer.parseInt(arrStr[1]));
        accountService.editAccount(accountUsers);
        return "redirect:/account";
    }
}
