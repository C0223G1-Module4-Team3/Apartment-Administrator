package com.example.case_study.service.employee;

import com.example.case_study.model.Position;
import com.example.case_study.repository.IPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PositionService implements IPositionService {
    @Autowired
    private IPositionRepository positionRepository;
    @Override
    public List<Position> displayListRole() {
        return positionRepository.findAll();
    }
}
