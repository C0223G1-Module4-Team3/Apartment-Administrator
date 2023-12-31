package com.example.case_study.service;

import com.example.case_study.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IContractService {
    Page<Contract> displayList(Pageable pageable);

    Page<Contract> displayListByRoom(int id, Pageable pageable);
    Page<Contract> displayListByRoomToDirector(int id, Pageable pageable);

    Page<Contract> displayListForDirector(Pageable pageable);

    String addContract(Contract contract);

    void deleteContract(Integer id);

    Optional<Contract> getContractById(Integer id);

    Contract getContract(Contract contract);

    boolean getContractToManager(int id);

    boolean getContractToDirector(int id);

    Page<Contract> getListToAccountant(Pageable pageable);

    List<Contract> findSuccessContract();
    List<Contract> findNotSuccessContract();

    List<Contract> findContractHome();

}
