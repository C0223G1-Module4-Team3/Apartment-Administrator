package com.example.case_study.service.facility;

import com.example.case_study.model.Facility;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IFacilityService {
    Page<Facility> findAll(Pageable pageable);
    List<Facility> display();
    void save(Facility facility);
    void edit(Facility facility);
    void delete(Integer id);
    Facility detailFacility(Integer id);

}
