package com.example.case_study.repository;

import com.example.case_study.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
    Page<Employee> findAllByFlagDeleteIsFalseAndAccountUserIsNull(Pageable pageable);
    Employee findById(int id);

    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNotNull();

    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNull();

    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNullAndGenderIsTrue();
    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNullAndGenderIsFalse();

    Employee findByPhoneNumber(String phone);
}
