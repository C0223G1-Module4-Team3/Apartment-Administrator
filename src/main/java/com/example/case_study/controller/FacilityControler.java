package com.example.case_study.controller;

import com.example.case_study.model.Employee;
import com.example.case_study.service.detail_room.IDetailRoomService;
import com.example.case_study.service.employee.IEmployeeService;
import com.example.case_study.service.facility.IFacilityService;
import com.example.case_study.service.room.IRoomService;
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
@RequestMapping("/facility")
public class FacilityControler {
    @Autowired
    IFacilityService facilityService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IDetailRoomService detailRoomService;
    @Autowired
    private IEmployeeService employeeService;
    @GetMapping("")
    public String displayRoom(Principal principal, Model model){
        model.addAttribute("facilityList",facilityService.display());
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        return "/facility/list";
    }
}
