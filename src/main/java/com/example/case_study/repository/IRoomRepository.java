package com.example.case_study.repository;

import com.example.case_study.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoomRepository extends JpaRepository<Room,Integer> {
    Optional<Room> findRoomById(int id);
}
