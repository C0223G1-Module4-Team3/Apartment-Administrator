package com.example.case_study.controller;

import com.example.case_study.dto.EmployeeDto;
import com.example.case_study.model.DetailRoomDto;
import com.example.case_study.model.Employee;
import com.example.case_study.model.Room;
import com.example.case_study.service.detail_room.IDetailRoomService;
import com.example.case_study.service.employee.IEmployeeService;
import com.example.case_study.service.facility.IFacilityService;
import com.example.case_study.service.room.IRoomService;
import com.example.case_study.untils.WebUltils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/room")
public class RoomControler {
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IFacilityService facilityService;
    @Autowired
    private IDetailRoomService detailRoomService;
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("")
    public String displayRoom(Principal principal, @PageableDefault(size = 5) Pageable pageable, Model model){
        model.addAttribute("roomList",roomService.display(pageable));
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        return "/room/list";
    }
    @GetMapping("detail/{id}")
    public String detailRoom(@PathVariable Integer id,Model model,Principal principal){
        model.addAttribute("roomList",roomService.findAll());
        model.addAttribute("detailRoom",roomService.detailRoom(id));
        model.addAttribute("detailRoomByIdRoom",detailRoomService.findByRoomId(id));
        model.addAttribute("totalFacility",detailRoomService.getAllFacilityInRoom(roomService.detailRoom(id).getId()));
//        ---them moi---
        DetailRoomDto detailRoomDto = new DetailRoomDto();
        detailRoomDto.setRoomId(roomService.detailRoom(id).getId());
        detailRoomDto.setFlagDelete(false);
        model.addAttribute("detailRoomDto", detailRoomDto);
        model.addAttribute("facilityList", facilityService.display());
        String userName = principal.getName();
        Employee employee = employeeService.findByPhone(userName);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        String userInfo = WebUltils.toString(loginedUser);
        model.addAttribute("employeeDetails", this.employeeService.findByPhone(userName));
        return "/room/detail";
    }
    @GetMapping("/update/{id}")
    public String showFormEditEmployee(@PathVariable int id, RedirectAttributes redirectAttributes, Model model){
        if (facilityService.detailFacility(id) == null){
            redirectAttributes.addFlashAttribute("message","Find not Found id");
            return "redirect:/room";
        }else {
            model.addAttribute("room",roomService.detailRoom(id));
            return "room/update";
        }
    }
    @PostMapping("update")
    public String update(@ModelAttribute Room room,RedirectAttributes redirectAttributes){
        roomService.edit(room);
        redirectAttributes.addFlashAttribute("msg","update success");
        return "redirect:/room";
    }
    @PostMapping("maintenance/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        roomService.maintenance(id);
        redirectAttributes.addFlashAttribute("msg", "Delete success");
        return "redirect:/room";
    }
}
