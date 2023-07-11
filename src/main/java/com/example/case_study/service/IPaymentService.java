package com.example.case_study.service;

import com.example.case_study.model.PayMent;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    List<PayMent> getPaymentsByContract(Integer id);

    Optional<PayMent> getPayment(Integer id);

    void pay(PayMent payMent);
}
