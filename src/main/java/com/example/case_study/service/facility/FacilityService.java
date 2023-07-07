package com.example.case_study.service.facility;

import com.example.case_study.model.Facility;
import com.example.case_study.repository.IFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class FacilityService implements IFacilityService{
    @Autowired
    private IFacilityRepository facilityRepository;
    @Override
    public List<Facility> display() {
        return facilityRepository.findAll();
    }

    @Override
    public void save(Facility facility) {

    }

    @Override
    public void edit(Facility facility) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Facility detailFacility(Integer id) {
        return facilityRepository.findById(id).get();
    }
}
