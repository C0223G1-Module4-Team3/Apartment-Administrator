package com.example.case_study.controller;

import com.example.case_study.model.DetailRoom;
import com.example.case_study.model.DetailRoomDto;
import com.example.case_study.model.Facility;
import com.example.case_study.model.Room;
import com.example.case_study.service.detail_room.IDetailRoomService;
import com.example.case_study.service.facility.IFacilityService;
import com.example.case_study.service.room.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("facilityCreate",new Facility());
        return "/facility/list";
    }
    @PostMapping("create")
    public String create(@ModelAttribute Facility facility, RedirectAttributes redirectAttributes) {
        facilityService.save(facility);
        redirectAttributes.addFlashAttribute("msg","Create success");
        return "redirect:/facility";
    }
    @PostMapping("update")
    public String update(@RequestParam("nameFacility") String nameFacility,@RequestParam("id") Integer id,RedirectAttributes redirectAttributes) {
        Facility facility = facilityService.detailFacility(id);
        facility.setName(nameFacility);
        facilityService.edit(facility);
        redirectAttributes.addFlashAttribute("msg", "Update Success");
        return "redirect:/facility";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        facilityService.delete(id);
        redirectAttributes.addFlashAttribute("msg", "Delete success");
        return "redirect:/facility";
    }
}
