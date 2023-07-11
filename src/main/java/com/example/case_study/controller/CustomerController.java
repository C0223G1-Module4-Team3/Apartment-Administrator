package com.example.case_study.controller;

import com.example.case_study.dto.CustomerDto;
import com.example.case_study.dto.EmployeeDto;
import com.example.case_study.model.Customer;
import com.example.case_study.model.Employee;
import com.example.case_study.service.IAccountService;
import com.example.case_study.service.customer.ICustomerService;
import com.example.case_study.service.employee.IEmployeeService;
import com.example.case_study.untils.WebUltils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;


    @GetMapping("")
    public String display(Principal principal, Model model, @PageableDefault(size = 6, sort = "id") Pageable pageable) {
        Page<Customer> customers = customerService.display(pageable);
        model.addAttribute("customers", customers);
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        return "/customer/list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        CustomerDto customer = new CustomerDto();
        model.addAttribute("customer", customer);
        return "customer/add";
    }

    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable int id, Model model) {
        Customer customer = customerService.showCustomerEdit(id);
        if (customer == null) {
            model.addAttribute("msg", "ID not found.  ");
            return "customer/list";
        } else {
            model.addAttribute("customer", customer);
            return "customer/edit";
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model, Principal principal) {
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        Customer customer = customerService.showCustomerEdit(id);
        if (customer == null) {
            model.addAttribute("msg", "ID not found.  ");
            return "customer/list";
        } else {
            model.addAttribute("customer", customer);
            return "customer/detail";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Customer customer = customerService.showCustomerEdit(id);
        if (customer == null) {
            redirectAttributes.addFlashAttribute("msg", "ID not found. ");
            return "customer/list";
        } else {
            customerService.delete(id);
            redirectAttributes.addFlashAttribute("msg", "Successfully deleted ");
            return "redirect:/customer";
        }
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute(name = "customer") CustomerDto customerDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        new CustomerDto().validate(customerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "customer/add";
        }
        if (customerDto == null) {
            redirectAttributes.addAttribute("msg", "Object does not exist.  ");
            return "customer/add";
        } else {
            List<Customer> customers = customerService.displayListCustomer();
            for (int i = 0; i < customers.size(); i++) {
                if (customerDto.getCitizenId().equals(customers.get(i).getCitizenId())) {
                    model.addAttribute("customer", customerDto);
                    model.addAttribute("msg", "ID card already exists. ");
                    return "customer/add";
                }
                if (customerDto.getPhoneNumber().equals(customers.get(i).getPhoneNumber())) {
                    model.addAttribute("customer", customerDto);
                    model.addAttribute("msg", "Phone number already exists. ");
                    return "customer/add";
                }
                if (customerDto.getEmail().equals(customers.get(i).getEmail())) {
                    model.addAttribute("customer", customerDto);
                    model.addAttribute("msg", "Email already exists.  ");
                    return "customer/add";
                }
            }
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            customerService.add(customer);
            redirectAttributes.addFlashAttribute("msg", "Successfully added");
            return "redirect:/customer";
        }
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute CustomerDto customerDto, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes) {
        new CustomerDto().validate(customerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customerDto);
            return "customer/edit";
        }
        if (customerDto == null) {
            redirectAttributes.addAttribute("msg", "ID not found. ");
            return "customer/edit";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            customerService.edit(customer);
            redirectAttributes.addFlashAttribute("msg", "Successfully edited");
            return "redirect:/customer";
        }
    }

    @GetMapping("/search")
    public String search(Principal principal, Model model, @PageableDefault(size = 6) Pageable pageable,
                         @RequestParam(value = "name", defaultValue = "")
                         String name, @RequestParam(value = "phoneNumber", defaultValue = "") String phoneNumber,
                         @RequestParam(value = "citizenId", defaultValue = "") String citizenId) {
        Page<Customer> customers = customerService.search(pageable, name, phoneNumber, citizenId);
        model.addAttribute("customers", customers);
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        return "customer/list";
    }
}
