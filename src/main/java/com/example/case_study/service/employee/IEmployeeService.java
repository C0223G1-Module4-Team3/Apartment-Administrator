package com.example.case_study.service.employee;

import com.example.case_study.model.Employee;
import com.example.case_study.model.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeeService {
    Page<Employee> displayListEmployee(Pageable pageable);



    void createEmployee(Employee employee);

    boolean deleteEmployee(int id);

    void editEmployee(Employee employee);

    List<Employee> searchEmployeeByName(String name);

    Employee getEmployeeById(int id);
}
