package com.example.case_study.service.employee;

import com.example.case_study.model.Employee;
import com.example.case_study.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSevice implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public Page<Employee> displayListEmployee(Pageable pageable) {
//        return employeeRepository.findAll(pageable);
        return employeeRepository.findAllByFlagDeleteIsFalse(pageable);
    }


    @Override
    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public boolean deleteEmployee(int id) {
        Employee employee = getEmployeeById(id);
        employee.setFlagDelete(true);
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public void editEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> searchEmployeeByName(String name) {
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }
}
