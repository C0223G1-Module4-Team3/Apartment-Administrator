package com.example.case_study.repository;

import com.example.case_study.model.DetailRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetailRoomRepository extends JpaRepository<DetailRoom, Integer> {
}
