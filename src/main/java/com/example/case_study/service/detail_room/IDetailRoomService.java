package com.example.case_study.service.detail_room;

import com.example.case_study.model.DetailRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDetailRoomService {
    List<DetailRoom> display();
    void save(DetailRoom detailRoom);
    void edit(DetailRoom detailRoom);
    List<DetailRoom> findByRoomId(Integer id);
    void editAmount(DetailRoom detailRoom);
    boolean checkFacilityId(DetailRoom detailRoom);
    Integer getAllFacilityInRoom(Integer id);
    DetailRoom findById(Integer id);
    void delete(Integer id);
}
