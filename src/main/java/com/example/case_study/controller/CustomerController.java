package com.example.case_study.controller;

import com.example.case_study.dto.CustomerDto;
import com.example.case_study.model.Customer;
import com.example.case_study.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public String display(Model model, @PageableDefault(size = 6, sort = "name") Pageable pageable) {
        Page<Customer> customers = customerService.display(pageable);
        model.addAttribute("customers", customers);
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
            model.addAttribute("msg", "Không tìm thấy id. ");
            return "customer/list";
        } else {
            model.addAttribute("customer", customer);
            return "customer/edit";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Customer customer = customerService.showCustomerEdit(id);
        if (customer == null) {
            redirectAttributes.addFlashAttribute("msg", "Không tìm thấy id.");
            return "customer/list";
        } else {
            customerService.delete(id);
            redirectAttributes.addFlashAttribute("msg", "xoa thanh cong");
            return "redirect:/customer";
        }
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute CustomerDto customerDto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customerDto);
            return "customer/add";
        }
        if (customerDto == null) {
            redirectAttributes.addAttribute("msg", "Đối tượng không tồn tại. ");
            return "customer/edit";
        } else {
//            List<Customer> customers = customerService.displayListCustomer();
//            for (int i = 0; i < customers.size(); i++) {
//                if (customerDto.getCitizenId().equals(customers.get(i).getCitizenId())) {
//                    model.addAttribute("msg", "cmnd đã tồn tại. ");
//                    return "customer/add";
//                }
//                if (customerDto.getPhoneNumber().equals(customers.get(i).getPhoneNumber())) {
//                    model.addAttribute("msg", " số điện thoại đã tồn tại. ");
//                    return "customer/add";
//                }
//                if (customerDto.getEmail().equals(customers.get(i).getEmail())) {
//                    model.addAttribute("msg", "email đã tồn tại. ");
//                    return "customer/add";
//                }
//            }
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            customerService.add(customer);
            redirectAttributes.addFlashAttribute("msg", "them moi thanh cong");
            return "redirect:/customer";
        }
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute CustomerDto customerDto, BindingResult bindingResult, Model model,
                       RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customerDto);
            return "customer/edit";
        }
        if (customerDto == null) {
            redirectAttributes.addAttribute("msg", "Không tìm thấy id. ");
            return "customer/edit";
        } else {
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            customerService.edit(customer);
            redirectAttributes.addFlashAttribute("msg", "sua moi thanh cong");
            return "redirect:/customer";
        }
    }

    @GetMapping("/search")
    public String search(Model model, @PageableDefault(size = 8) Pageable pageable,
                         @RequestParam(value = "name", defaultValue = "")
                         String name, @RequestParam(value = "phoneNumber", defaultValue = "") String phoneNumber,
                         @RequestParam(value = "citizenId", defaultValue = "") String citizenId) {
        Page<Customer> customers = customerService.search(pageable, name, phoneNumber, citizenId);
        model.addAttribute("customers", customers);
        return "redirect:/customer";
    }
}
