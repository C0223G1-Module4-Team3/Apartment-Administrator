package com.example.case_study.service.employee;

import com.example.case_study.model.RoleUser;
import com.example.case_study.repository.IRoleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class RoleUserService implements IRoleUserService{
    @Autowired
    private IRoleUserRepository roleUserRepository;
    @Override
    public List<RoleUser> findAll() {
        return roleUserRepository.findAll();
    }
}
