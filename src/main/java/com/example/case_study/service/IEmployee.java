package com.example.case_study.service;

import com.example.case_study.model.Employee;

public interface IEmployee {
    Employee findByAccountUser_Phone(String phone);
}
