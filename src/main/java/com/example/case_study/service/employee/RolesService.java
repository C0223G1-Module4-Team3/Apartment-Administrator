package com.example.case_study.service.employee;

import com.example.case_study.model.Roles;
import com.example.case_study.repository.IRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RolesService implements IRolesService{
    @Autowired
    private IRolesRepository rolesRepository;
    @Override
    public List<Roles> displayListRole() {
        return rolesRepository.findAll();
    }
}
