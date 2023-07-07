package com.example.case_study.repository;

import com.example.case_study.model.PayMent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IPaymentRepository extends JpaRepository<PayMent, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into payment(serial,paided,contract_id) value (:serial ,0,:id )", nativeQuery = true)
    void addPayment(@Param(value = "serial") int serial,@Param(value = "id") int id);

    List<PayMent> findAllByContract_Id(Integer contract_id);
}
