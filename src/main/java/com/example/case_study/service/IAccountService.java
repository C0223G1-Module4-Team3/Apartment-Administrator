package com.example.case_study.service;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.RoleUser;

public interface IAccountService {

    AccountUser findByPhone(String phone);
    RoleUser findRoleById(int id);

    void createAccount(AccountUser accountUser);
}
