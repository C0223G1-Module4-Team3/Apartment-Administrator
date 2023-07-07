package com.example.case_study.service.detail_room;

import com.example.case_study.model.DetailRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDetailRoomService {
    Page<DetailRoom> display(Pageable pageable);
    void save(DetailRoom detailRoom);
    void edit(DetailRoom detailRoom);
    Page<DetailRoom> findByRoomId(Integer id,Pageable pageable);
    void editAmount(DetailRoom detailRoom);
    boolean checkFacilityId(DetailRoom detailRoom);
    Integer getAllFacilityInRoom(Integer id);
    DetailRoom findById(Integer id);
    void delete(Integer id);
}
