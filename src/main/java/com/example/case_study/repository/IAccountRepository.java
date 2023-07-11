package com.example.case_study.repository;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IAccountRepository extends JpaRepository<AccountUser,Integer>{
    AccountUser findAccountUserByPhoneNumber(String phone);
    List<AccountUser> findByPhoneNumber(String phone);

}
