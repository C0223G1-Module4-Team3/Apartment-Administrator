package com.example.case_study.repository;

import com.example.case_study.model.DetailRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Converter;
import java.util.List;
@Repository
public interface IDetailRoomRepository extends JpaRepository<DetailRoom, Integer> {
    List<DetailRoom> findAll();
    @Query(value = "SELECT * FROM details_room as dr WHERE dr.id_room =:id", nativeQuery = true)
    List<DetailRoom> findByRoomId(@Param(value = "id") Integer id);

    @Transactional
    @Modifying
    @Query (value = "insert into details_room(id_room, facility_id, amount) values (:roomId, :facilityId, :amount)", nativeQuery = true)
    void saveRoomDetail(@Param(value = "roomId")Integer roomId, @Param(value = "facilityId")Integer facilityId, @Param("amount")Integer amount);
    @Modifying
    @Transactional
    @Query(value = "update details_room as dr set is_flag_delete = 1 where dr.detail_room_id = :id ", nativeQuery = true)
    void isDelete(@Param(value = "id") Integer id);
}
