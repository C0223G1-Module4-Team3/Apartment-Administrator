package com.example.case_study.service;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.RoleUser;
import com.example.case_study.repository.IAccountRepository;
import com.example.case_study.repository.IRoleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleUserRepository roleUser;

    @Override
    public AccountUser findByPhone(String phone) {
        if (accountRepository.findAccountUserByPhone(phone) == null ){
            return null;
        }
        return accountRepository.findAccountUserByPhone(phone);
    }

    @Override
    public RoleUser findRoleById(int id) {
        return roleUser.findById(id);
    }

    @Override
    public void createAccount(AccountUser accountUser) {
        accountRepository.save(accountUser);
    }
}
