package com.example.case_study.controller;

import com.example.case_study.service.detail_room.IDetailRoomService;
import com.example.case_study.service.facility.IFacilityService;
import com.example.case_study.service.room.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facility")
public class FacilityControler {
    @Autowired
    IFacilityService facilityService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IDetailRoomService detailRoomService;
    @GetMapping("")
    public String displayRoom(Model model){
        model.addAttribute("facilityList",facilityService.display());
        return "/facility/list";
    }
}
