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
    public List<Employee> displayListEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> displayListEmployee(Pageable pageable) {
//        return employeeRepository.findAll(pageable);
        return employeeRepository.findAllByFlagDeleteIsFalseAndAccountUserIsNull(pageable);
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
    public Page<Employee> searchEmployeeByName(Pageable pageable ,String namer,String citizenId, String phoneNumbe) {
        return employeeRepository.findAllByNameOrPhoneNumberOrCitizenId(pageable, namer,citizenId,phoneNumbe);
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> displayListEmployeeHaveAccount() {
        return employeeRepository.findAllByFlagDeleteFalseAndAccountUserIsNotNull();
    }

    @Override
    public List<Employee> displayEmployeeHome() {
        return employeeRepository.findAllByFlagDeleteFalseAndAccountUserIsNull();
    }

    @Override
    public List<Employee> showMaleEmployee() {
        return employeeRepository.findAllByFlagDeleteFalseAndAccountUserIsNullAndGenderIsTrue();
    }

    @Override
    public List<Employee> showFemaleEmployee() {
        return employeeRepository.findAllByFlagDeleteFalseAndAccountUserIsNullAndGenderIsFalse();
    }

    @Override
    public Employee findByPhone(String phone) {
        return employeeRepository.findByPhoneNumber(phone);
    }
}
