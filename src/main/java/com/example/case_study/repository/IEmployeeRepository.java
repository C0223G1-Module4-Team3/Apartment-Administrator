package com.example.case_study.repository;

import com.example.case_study.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update employee set position_id=:position where id=:id",nativeQuery = true)
    void setEmployee(@Param(value = "position") int position,@Param(value = "id") int id);
    Page<Employee> findAllByFlagDeleteIsFalseAndAccountUserIsNull(Pageable pageable);

    Employee findById(int id);

    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNotNullAndAccountUserStatusIsFalse();

    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNull();

    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNullAndGenderIsTrue();

    List<Employee> findAllByFlagDeleteFalseAndAccountUserIsNullAndGenderIsFalse();

    Employee findByPhoneNumber(String phone);

    @Query(value = "select * from employee as c where (c.name like concat('%', :name ,'%') or c.name='') and( c.phone_number like concat('%', :phoneNumber ,'%') or c.phone_number='')and (c.citizen_id like concat('%', :citizenId ,'%') or c.citizen_id='')", nativeQuery = true)
    Page<Employee> findAllByNameOrPhoneNumberOrCitizenId
            (Pageable pageable, @Param(value = "name") String name, @Param(value = "citizenId") String citizenId, @Param(value = "phoneNumber") String phoneNumber);

    @Query(value = "select * from employee as c where(c.id=:id and c.position_id=5)", nativeQuery = true)
    Optional<Employee> findEmployeeByIdAndPositionIs5(@Param(value = "id") int id);
}
