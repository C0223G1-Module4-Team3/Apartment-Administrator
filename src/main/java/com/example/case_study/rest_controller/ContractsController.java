package com.example.case_study.rest_controller;

import com.example.case_study.model.Contract;
import com.example.case_study.service.IContractService;
import com.example.case_study.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/contracts")
public class ContractsController {
    @Autowired
    private IContractService contractService;


    @GetMapping("/{page}")
    ResponseEntity<Page<Contract>> displayContractList(@PageableDefault(size = 3) Pageable pageable, @PathVariable int page) {
        Page<Contract> pages = contractService.displayList(pageable.withPage(page));
        if (pages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @GetMapping("/director/{page}")
    ResponseEntity<Page<Contract>> displayContractListForDirector(@PageableDefault(size = 3) Pageable pageable, @PathVariable int page) {
        Page<Contract> pages = contractService.displayListForDirector(pageable.withPage(page));
        if (pages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @PostMapping("/manager-confirm/{id}")
    public ResponseEntity<?> confirmByManager(@PathVariable int id) {
        if (contractService.getContractToManager(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/director-confirm/{id}")
    public ResponseEntity<?> confirmByDirector(@PathVariable int id) {
        if (contractService.getContractToDirector(id)) {
            String msg = "confirm successful";
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteContract(@PathVariable int id) {
        if (contractService.getContractById(id).isPresent()) {
            contractService.deleteContract(id);
            String msg = "delete successful";
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/accountant/{page}")
    ResponseEntity<Page<Contract>> displayContractListToAccountant(@PageableDefault(size = 3) Pageable pageable, @PathVariable int page) {
        Page<Contract> pages = contractService.getListToAccountant(pageable.withPage(page));
        if (pages == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

}

