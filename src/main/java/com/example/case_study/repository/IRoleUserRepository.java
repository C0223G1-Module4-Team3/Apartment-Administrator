package com.example.case_study.repository;

import com.example.case_study.model.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleUserRepository extends JpaRepository<RoleUser,Integer> {

    RoleUser findById(int id);
}
