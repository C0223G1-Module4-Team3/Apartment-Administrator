package com.example.case_study.repository;

import com.example.case_study.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {


    List<Customer> findAllByFlagDeleteFalseAndGenderIsFalse();

    List<Customer> findAllByFlagDeleteFalseAndGenderIsTrue();

    Optional<Customer> findCustomerById(Integer id);
    @Modifying
    @Transactional
    @Query(value = "update customer as c set flag_delete = 1 where c.id = :id ", nativeQuery = true)
    void isDelete(@Param(value = "id") Integer id);

    @Query(value = "select * from customer as c where (c.name like concat('%', :name ,'%') or c.name='') and( c.phone_number like concat('%',:phoneNumber,'%') or c.phone_number='')and (c.citizen_id like concat('%',:citizenId,'%') or c.citizen_id='')", nativeQuery = true)
    Page<Customer> findAllByNameOrPhoneNumberOrCitizenId
            (Pageable pageable, @Param(value = "name") String name, @Param(value = "phoneNumber") String phoneNumber, @Param(value = "citizenId") String citizenId);
}
