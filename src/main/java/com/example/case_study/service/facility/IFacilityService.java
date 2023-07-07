package com.example.case_study.service.facility;

import com.example.case_study.model.Facility;

import java.util.List;

public interface IFacilityService {
    List<Facility> display();
    void save(Facility facility);
    void edit(Facility facility);
    void delete(Integer id);
    Facility detailFacility(Integer id);
}
