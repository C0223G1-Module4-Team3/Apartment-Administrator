package com.example.case_study.service;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.RoleUser;
import com.example.case_study.repository.IAccountRepository;
import com.example.case_study.repository.IRoleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Lazy
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleUserRepository roleUser;

    @Override
    public AccountUser findByPhone(String phone) {
        if (accountRepository.findAccountUserByPhoneNumber(phone) == null ){
            return null;
        }
        return accountRepository.findAccountUserByPhoneNumber(phone);
    }

    @Override
    public RoleUser findRoleById(int id) {
        return roleUser.findById(id);
    }

    @Override
    public void createAccount(AccountUser accountUser) {
        accountRepository.save(accountUser);
    }

    @Override
    public List<AccountUser> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<AccountUser> findById(int id) {
        return accountRepository.findById(id);
    }
}
