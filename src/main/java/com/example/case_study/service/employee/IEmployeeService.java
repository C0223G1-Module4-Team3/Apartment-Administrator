package com.example.case_study.service.employee;

import com.example.case_study.model.Customer;
import com.example.case_study.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEmployeeService {
    Page<Employee> displayListEmployee(Pageable pageable);

    void createEmployee(Employee employee);

    boolean deleteEmployee(int id);

    void editEmployee(Employee employee);

    Page<Employee> searchEmployeeByName(Pageable pageable,String name,String citizenId, String phoneNumber);

    Employee getEmployeeById(int id);

    List<Employee> displayListEmployeeHaveAccount();
    List<Employee> displayEmployeeHome();

    List<Employee> showMaleEmployee();

    List<Employee> showFemaleEmployee();

    Employee findByPhone(String phone);

    boolean deleteAccount(int id );
}
