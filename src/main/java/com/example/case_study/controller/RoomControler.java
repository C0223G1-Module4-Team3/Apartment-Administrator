package com.example.case_study.controller;

import com.example.case_study.model.DetailRoomDto;
import com.example.case_study.service.detail_room.IDetailRoomService;
import com.example.case_study.service.facility.IFacilityService;
import com.example.case_study.service.room.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room")
public class RoomControler {
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IFacilityService facilityService;
    @Autowired
    private IDetailRoomService detailRoomService;
    @GetMapping("")
    public String displayRoom(@PageableDefault(size = 5) Pageable pageable, Model model){
        model.addAttribute("roomList",roomService.display(pageable));
        return "/room/list";
    }
    @GetMapping("detail/{id}")
    public String detailRoom(@PathVariable Integer id,Model model){
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
        return "/room/detail";
    }
}
