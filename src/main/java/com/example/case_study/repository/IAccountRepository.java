package com.example.case_study.repository;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<AccountUser, Integer> {
    AccountUser findAccountUserByPhoneNumber(String phone);

    List<AccountUser> findByPhoneNumber(String phone);

    AccountUser getAccountById(int id);

    @Transactional
    @Modifying
    @Query(value = "update account_user set role_user_id=:user where id=:id", nativeQuery = true)
    void setAccount(@Param(value = "user") int role, @Param(value = "id") int id);
}
