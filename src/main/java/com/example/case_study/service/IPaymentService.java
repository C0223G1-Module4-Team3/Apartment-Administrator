package com.example.case_study.service;

import com.example.case_study.model.PayMent;

import java.util.List;

public interface IPaymentService {
    List<PayMent> getPaymentsByContract(Integer id);
}
