package com.example.case_study.service;

import com.example.case_study.model.PayMent;
import com.example.case_study.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    IPaymentRepository paymentRepository;
    @Override
    public List<PayMent> getPaymentsByContract(Integer id) {
        return paymentRepository.findAllByContract_Id(id);
    }

    @Override
    public Optional<PayMent> getPayment(Integer id) {
        return paymentRepository.getPayMentById(id);
    }

    @Override
    public void pay(PayMent payMent) {
        int serial = payMent.getSerial();
        int id = payMent.getContract().getId();
        paymentRepository.updatePayment(serial,id);
    }

}
