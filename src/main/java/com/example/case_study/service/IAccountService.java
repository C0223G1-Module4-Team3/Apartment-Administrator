package com.example.case_study.service;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.Employee;
import com.example.case_study.model.RoleUser;
import jdk.nashorn.internal.runtime.options.Option;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    void setRoleAccount(int id, int role);

    AccountUser findByPhone(String phone);
    RoleUser findRoleById(int id);

    void createAccount(AccountUser accountUser);
    List<AccountUser> findAll();
    Optional<AccountUser> findById(int id);

    AccountUser getAccountById(int id);

    void editAccount(AccountUser accountUser);
}
