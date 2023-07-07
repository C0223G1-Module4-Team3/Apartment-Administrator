package com.example.case_study.service;

import com.example.case_study.model.PayMent;
import com.example.case_study.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentService implements IPaymentService{
    @Autowired
    IPaymentRepository paymentRepository;
    @Override
    public List<PayMent> getPaymentsByContract(Integer id) {
        return paymentRepository.findAllByContract_Id(id);
    }
}
