package com.example.case_study.service.room;

import com.example.case_study.model.Room;

import java.util.List;

public interface IRoomService {
    List<Room> display();
    Room detailRoom(Integer id);
    void edit(Room room);
    void maintenance(Integer id);
    void maintenanceFinished(Integer id);
}
