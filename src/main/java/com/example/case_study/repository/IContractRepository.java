package com.example.case_study.repository;

import com.example.case_study.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IContractRepository extends JpaRepository<Contract, Integer> {
    Page<Contract> findAllByFlagDeleteIsFalse(Pageable pageable);

    @Query(value = "select * from contracts where (room_id = :id and flag_delete = 0)",nativeQuery = true)
    Page<Contract> findContractsByRoom(@Param(value = "id") int id,Pageable pageable);

    @Query(value = "select * from contracts where (room_id = :id and flag_delete = 0 and manager_confirm =1)",nativeQuery = true)
    Page<Contract> findContractsByRoomToDirector(@Param(value = "id") int id,Pageable pageable);
    Optional<Contract> getContractByIdAndFlagDeleteIsFalse(Integer id);
    Optional<Contract> getContractByIdAndFlagDeleteIsFalseAndManagerConfirmIsFalse(Integer id);
    Optional<Contract> getContractByIdAndFlagDeleteIsFalseAndManagerConfirmIsTrueAndDirectorConfirmIsFalse(Integer id);
    Page<Contract> findAllByFlagDeleteIsFalseAndManagerConfirmIsTrueAndDirectorConfirmIsTrue(Pageable pageable);

    Page<Contract> findAllByFlagDeleteIsFalseAndManagerConfirmIsTrue(Pageable pageable);

    List<Contract> findAllByFlagDeleteFalseAndManagerConfirmIsFalseOrDirectorConfirmIsFalse();
    List<Contract> findAllByFlagDeleteFalseAndManagerConfirmIsTrueAndDirectorConfirmIsTrue();

    List<Contract> findAllByFlagDeleteFalse();

    Optional<List<Contract>> findAllByFlagDeleteIsFalseAndManagerConfirmIsTrueAndDirectorConfirmIsTrue();

    @Query(value = "select * from contracts where (room_id = :id and flag_delete = 0) order by date_start desc limit 1",nativeQuery = true)
    Optional<Contract> findContractByRoom(@Param(value = "id") int id);
}
