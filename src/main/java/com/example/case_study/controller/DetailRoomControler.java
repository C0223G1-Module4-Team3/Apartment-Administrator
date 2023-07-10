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
@RequestMapping("room/facility")
public class DetailRoomControler {
    @Autowired
    IFacilityService facilityService;
    @Autowired
    private IRoomService roomService;
    @Autowired
    private IDetailRoomService detailRoomService;

//    @GetMapping("create/{id}")
//    public String showFormCreate(@PathVariable Integer id, Model model) {
//        DetailRoomDto detailRoomDto = new DetailRoomDto();
//        detailRoomDto.setRoomId(roomService.detailRoom(id).getId());
//        model.addAttribute("detailRoomDto", detailRoomDto);
//        model.addAttribute("facilityList", facilityService.display());
//        return "detail_room/update";
//    }

    @PostMapping("create")
    public String create(@ModelAttribute DetailRoomDto detailRoomDto, RedirectAttributes redirectAttributes) {
        Integer amount = detailRoomDto.getAmount();
        Room room = roomService.detailRoom(detailRoomDto.getRoomId());
        Facility facility = facilityService.detailFacility(detailRoomDto.getFacilityId());
        DetailRoom detailRoom = new DetailRoom(room, facility, amount);
        if (detailRoomService.checkFacilityId(detailRoom)) {
            detailRoomService.editAmount(detailRoom);
            redirectAttributes.addFlashAttribute("msg", "Update success");
        } else {
            detailRoomService.save(detailRoom);
            redirectAttributes.addFlashAttribute("msg", "Create success");
        }
        return "redirect:/room/detail/" + detailRoomDto.getRoomId();
    }

    @GetMapping("update/{id}")
    public String showFormUpdate(@PathVariable Integer id, Model model) {
        model.addAttribute("detailRoomUpdate", detailRoomService.findById(id));
        return "detail_room/update";
    }

    @PostMapping("update")
    public String update(@ModelAttribute DetailRoom detailRoom, RedirectAttributes redirectAttributes) {
        detailRoomService.edit(detailRoom);
        redirectAttributes.addFlashAttribute("msg", "Update Success");
        return "redirect:/room/detail/" + detailRoom.getRoom().getId();
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id,@RequestParam("idRoom") Integer idRoom, RedirectAttributes redirectAttributes) {
        detailRoomService.delete(id);
        redirectAttributes.addFlashAttribute("msg","Delete success");
        return "redirect:/room/detail/" + idRoom;
    }
}
