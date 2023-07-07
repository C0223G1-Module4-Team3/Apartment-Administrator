package com.example.case_study.repository;

import com.example.case_study.model.KindContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IKindContractRepository extends JpaRepository<KindContract,Integer> {
}
