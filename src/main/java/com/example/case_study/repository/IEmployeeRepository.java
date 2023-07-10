package com.example.case_study.repository;

import com.example.case_study.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
    Page<Employee> findAllByFlagDeleteIsFalseAndAccountUserIsNull(Pageable pageable);
    Employee findById(int id);

    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNotNull();

    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNull();

    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNullAndGenderIsTrue();
    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNullAndGenderIsFalse();

    Employee findByPhoneNumber(String phone);
    @Query(value = "select * from employee as c where (c.name like concat('%', :name ,'%') or c.name='') and( c.phone_number like concat('%', :phoneNumber ,'%') or c.phone_number='')and (c.citizen_id like concat('%', :citizenId ,'%') or c.citizen_id='')", nativeQuery = true)

    Page<Employee> findAllByNameOrPhoneNumberOrCitizenId
            (Pageable pageable, @Param(value = "name") String name, @Param(value = "citizenId") String citizenId, @Param(value = "phoneNumber") String phoneNumber);
}
