package com.example.case_study.service;

import com.example.case_study.model.*;
import com.example.case_study.repository.*;
import com.example.case_study.schedule.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService implements IContractService {
    @Autowired
    private IContractRepository contractRepository;
    @Autowired
    private IEmployeeRepository employeeRepository;
    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IRoomRepository roomRepository;
    @Autowired
    private IPaymentRepository paymentRepository;

    @Override
    public Page<Contract> displayList(Pageable pageable) {
        return contractRepository.findAllByFlagDeleteIsFalse(pageable);
    }

    @Override
    public Page<Contract> displayListForDirector(Pageable pageable) {
        return contractRepository.findAllByFlagDeleteIsFalseAndManagerConfirmIsTrue(pageable);
    }

    @Override
    public String addContract(Contract contract) {
        if (!customerRepository.findCustomerById(contract.getCustomer().getId()).isPresent()) {
            return "can't find customer";
        }
        if (!employeeRepository.findEmployeeByIdAndPositionIs5(contract.getEmployee().getId()).isPresent()) {
            return "can't find sale";
        }
        if (!roomRepository.findById(contract.getRoom().getId()).isPresent()) {
            return "can't find room";
        }
        if (contractRepository.findContractByRoom(contract.getRoom().getId()).isPresent()) {
            Contract contract1 = contractRepository.findContractByRoom(contract.getRoom().getId()).get();
            int month = 0;
            switch (contract1.getKindContract().getId()) {
                case 1:
                    month = 1 * contract1.getPeriod();
                    break;
                case 2:
                    month = 3 * contract1.getPeriod();
                    break;
                case 3:
                    month = 12 * contract1.getPeriod();
                    break;
                default:
                    break;
            }
            String dateEnd = Schedule.addMonthsToLocalDateString(contract1.getDate_start(),month);
            LocalDate localDate1 = LocalDate.parse(dateEnd);
            LocalDate localDate = LocalDate.parse(contract.getDate_start());
            int result = localDate.compareTo(localDate1);
            if(result<=0){
                return "This room was still under contract at the time your new contract started";
            }
        }
        contractRepository.save(contract);
        return "create contract successful";

    }

    @Override
    public void deleteContract(Integer id) {
        Contract contract = contractRepository.getContractByIdAndFlagDeleteIsFalse(id).get();
        contract.setFlagDelete(true);
        contractRepository.save(contract);
    }

    @Override
    public Optional<Contract> getContractById(Integer id) {
        return contractRepository.getContractByIdAndFlagDeleteIsFalse(id);
    }

    @Override
    public Contract getContract(Contract contract) {
        contract.setEmployee(employeeRepository.findById(contract.getEmployee().getId()).get());
        contract.setCustomer(customerRepository.findById(contract.getCustomer().getId()).get());
        contract.setRoom(roomRepository.findById(contract.getCustomer().getId()).get());
        return contract;
    }

    @Override
    public boolean getContractToManager(int id) {
        if (contractRepository.getContractByIdAndFlagDeleteIsFalseAndManagerConfirmIsFalse(id).isPresent()) {
            Contract contract = contractRepository.getContractByIdAndFlagDeleteIsFalseAndManagerConfirmIsFalse(id).get();
            contract.setManagerConfirm(true);
            contractRepository.save(contract);
            return true;
        }
        return false;
    }

    @Override
    public boolean getContractToDirector(int id) {
        if (contractRepository.getContractByIdAndFlagDeleteIsFalseAndManagerConfirmIsTrueAndDirectorConfirmIsFalse(id).isPresent()) {
            Contract contract = contractRepository.getContractByIdAndFlagDeleteIsFalseAndManagerConfirmIsTrueAndDirectorConfirmIsFalse(id).get();
            contract.setDirectorConfirm(true);
            contractRepository.save(contract);
            int a = 0;
            switch (contract.getKindContract().getId()) {
                case 1:
                    a = 1;
                    break;
                case 2:
                    a = 3;
                    break;
                case 3:
                    a = 12;
                    break;
                default:
                    break;
            }
            a *= contract.getPeriod();
            for (int i = 1; i <= a; i++) {
                paymentRepository.addPayment(i, contract.getId());
            }
            return true;
        }
        return false;
    }

    @Override
    public Page<Contract> getListToAccountant(Pageable pageable) {
        Page<Contract> originalPage = contractRepository.findAllByFlagDeleteIsFalseAndManagerConfirmIsTrueAndDirectorConfirmIsTrue(pageable);
        int pageNumber = originalPage.getNumber();
        int pageSize = originalPage.getSize();
        List<Contract> originalList = originalPage.getContent();
        List<Contract> modifiedList = new ArrayList<>(originalList);
        for (int i = 0; i < modifiedList.size(); i++) {
            if (!paymentRepository.getPayMentById(modifiedList.get(i).getId()).isPresent()) {
                modifiedList.remove(i);
                i--;
            }
        }

        return new PageImpl<>(modifiedList, PageRequest.of(pageNumber, pageSize), originalPage.getTotalElements());
    }

    @Override
    public List<Contract> findSuccessContract() {
        return contractRepository.findAllByFlagDeleteFalseAndManagerConfirmIsTrueAndDirectorConfirmIsTrue();
    }

    @Override
    public List<Contract> findNotSuccessContract() {
        return contractRepository.findAllByFlagDeleteFalseAndManagerConfirmIsFalseOrDirectorConfirmIsFalse();
    }

    @Override
    public List<Contract> findContractHome() {
        return contractRepository.findAllByFlagDeleteFalse();
    }
}
