package com.example.case_study.repository;

import com.example.case_study.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IRoomRepository extends JpaRepository<Room,Integer> {
    Optional<Room> findRoomById(int id);
    @Modifying
    @Transactional
    @Query(value = "update room as r set maintenance = 1 where r.id_room = :id ", nativeQuery = true)
    void isDelete(@Param(value = "id") Integer id);
}
