package com.example.case_study.repository;

import com.example.case_study.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IContractRepository extends JpaRepository<Contract, Integer> {
    Page<Contract> findAllByFlagDeleteIsFalse(Pageable pageable);
    Optional<Contract> getContractByIdAndFlagDeleteIsFalse(Integer id);
    Optional<Contract> getContractByIdAndFlagDeleteIsFalseAndManagerConfirmIsFalse(Integer id);
    Optional<Contract> getContractByIdAndFlagDeleteIsFalseAndManagerConfirmIsTrueAndDirectorConfirmIsFalse(Integer id);
    Page<Contract> findAllByFlagDeleteIsFalseAndManagerConfirmIsTrueAndDirectorConfirmIsTrue(Pageable pageable);
}
