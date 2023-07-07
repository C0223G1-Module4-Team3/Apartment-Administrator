package com.example.case_study.repository;

import com.example.case_study.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findByAccountUser_Phone(String phone);
}
