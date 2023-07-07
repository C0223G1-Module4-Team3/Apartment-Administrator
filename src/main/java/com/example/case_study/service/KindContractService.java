package com.example.case_study.service;

import com.example.case_study.model.KindContract;
import com.example.case_study.repository.IKindContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KindContractService implements IKindContractService{
    @Autowired
    private IKindContractRepository kindContractRepository;
    @Override
    public List<KindContract> getList() {
        return kindContractRepository.findAll();
    }
}
