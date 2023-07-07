package com.example.case_study.rest_controller;

import com.example.case_study.model.PayMent;
import com.example.case_study.service.IPaymentService;
import com.example.case_study.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentsController {
    @Autowired
    private IPaymentService paymentService;
    @GetMapping("/{id}")
    public ResponseEntity<List<PayMent>> getList(@PathVariable int id){
        if(paymentService.getPaymentsByContract(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paymentService.getPaymentsByContract(id),HttpStatus.OK);
    }

}
