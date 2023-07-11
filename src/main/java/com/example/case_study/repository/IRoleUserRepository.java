package com.example.case_study.repository;

import com.example.case_study.model.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleUserRepository extends JpaRepository<RoleUser,Integer> {

    RoleUser findById(int id);
}
