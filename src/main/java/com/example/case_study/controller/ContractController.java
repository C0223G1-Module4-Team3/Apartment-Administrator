package com.example.case_study.controller;

import com.example.case_study.dto.ContractCreationDto;
import com.example.case_study.model.Contract;
import com.example.case_study.model.Employee;
import com.example.case_study.service.IContractService;
import com.example.case_study.service.IKindContractService;
import com.example.case_study.service.employee.IEmployeeService;
import com.example.case_study.untils.WebUltils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private IContractService contractService;
    @Autowired
    private IKindContractService kindContractService;
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("")
    String displayContractList(Principal principal,Pageable pageable, Model model) {
        model.addAttribute("list", contractService.displayList(pageable));
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        return "/contract/displayContractList";
    }

    @GetMapping("/create")
    String addContract(Model model) {
        model.addAttribute("contractCreationDto", new ContractCreationDto());
        model.addAttribute("kindContracts", kindContractService.getList());
        return "/contract/createContract";
    }

    @PostMapping("/create")
    String addContract(@Valid @ModelAttribute ContractCreationDto contractCreationDto,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        new ContractCreationDto().validate(contractCreationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg", "create fail");
            return "redirect:/contract/create";
        }
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractCreationDto, contract);
        contractService.addContract(contract);
        redirectAttributes.addFlashAttribute("msg", "create successful");
        return "redirect:/contract/create";
    }

    @PostMapping("/detail")
    String detailContract(@RequestParam Integer id, Model model, RedirectAttributes redirectAttributes) {
        if (contractService.getContractById(id).isPresent()) {
            model.addAttribute("contract", contractService.getContractById(id).get());
            return "/contract/detailContract";
        }
        redirectAttributes.addFlashAttribute("msg", "can't find");
        return "redirect:/contract";
    }

    @GetMapping("/accountant")
    String displayContractList(Principal principal,Model model) {
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        return "/contract/acountant";
    }


}
