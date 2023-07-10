package com.example.case_study.rest_controller;

import com.example.case_study.model.PayMent;
import com.example.case_study.service.IPaymentService;
import com.example.case_study.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentsController {
    @Autowired
    private IPaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<List<PayMent>> getList(@PathVariable int id) {
        if (paymentService.getPaymentsByContract(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(paymentService.getPaymentsByContract(id), HttpStatus.OK);
    }

    @PostMapping("/pay/{id}")
    public ResponseEntity<?> pay(@PathVariable int id) {
        if (paymentService.getPayment(id).isPresent()) {
            paymentService.pay(paymentService.getPayment(id).get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
