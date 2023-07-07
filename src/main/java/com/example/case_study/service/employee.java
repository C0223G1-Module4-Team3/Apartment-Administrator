package com.example.case_study.service;

import com.example.case_study.model.Employee;
import com.example.case_study.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class employee implements IEmployee{
    @Autowired
    private IEmployeeRepository iEmployeeRepository;
    @Override
    public Employee findByAccountUser_Phone(String phone) {
        return iEmployeeRepository.findByAccountUser_Phone(phone);
    }
}
