package com.example.case_study.service.facility;

import com.example.case_study.model.Facility;
import com.example.case_study.repository.IFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Controller
public class FacilityService implements IFacilityService {
    @Autowired
    private IFacilityRepository facilityRepository;

    @Override
    public Page<Facility> findAll(Pageable pageable) {
        return facilityRepository.findAll(pageable);
    }

    @Override
    public List<Facility> display() {
        return facilityRepository.findAll();
    }

    @Override
    public void save(Facility facility) {
        facilityRepository.save(facility);
    }

    @Override
    public void edit(Facility facility) {
        facilityRepository.save(facility);
    }

    @Override
    public void delete(Integer id) {
        facilityRepository.isDelete(id);
    }

    @Override
    public Facility detailFacility(Integer id) {
        return facilityRepository.findById(id).get();
    }
}
