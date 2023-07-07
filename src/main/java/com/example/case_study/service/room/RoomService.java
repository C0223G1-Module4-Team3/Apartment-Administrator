package com.example.case_study.service.room;

import com.example.case_study.model.Room;
import com.example.case_study.repository.IRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoomService implements IRoomService{
    @Autowired
    private IRoomRepository roomRepository;
    @Override
    public List<Room> display() {
        return roomRepository.findAll();
    }

    @Override
    public Room detailRoom(Integer id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public void edit(Room room) {

    }

    @Override
    public void maintenance(Integer id) {

    }

    @Override
    public void maintenanceFinished(Integer id) {

    }
}
