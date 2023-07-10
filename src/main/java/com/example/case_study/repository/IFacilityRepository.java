package com.example.case_study.repository;

import com.example.case_study.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IFacilityRepository extends JpaRepository<Facility,Integer> {
    @Modifying
    @Transactional
    @Query(value = "update facility as f set is_flag_delete = 1 where f.facility_id = :id ", nativeQuery = true)
    void isDelete(@Param(value = "id") Integer id);
}
