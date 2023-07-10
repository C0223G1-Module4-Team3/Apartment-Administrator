package com.example.case_study.service.room;

import com.example.case_study.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoomService {
    Page<Room> display(Pageable pageable);
    List<Room> findAll();
    Room detailRoom(Integer id);
    void edit(Room room);
    void maintenance(Integer id);
    void maintenanceFinished(Integer id);
}
