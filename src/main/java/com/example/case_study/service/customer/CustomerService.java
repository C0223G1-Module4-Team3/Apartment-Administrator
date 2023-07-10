package com.example.case_study.service.customer;

import com.example.case_study.model.Customer;
import com.example.case_study.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Page<Customer> display(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> displayListCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> search(Pageable pageable, String name, String phoneNumber, String citizenId) {
        return customerRepository.findAllByNameOrPhoneNumberOrCitizenId(pageable,name,phoneNumber,citizenId);
    }

    @Override
    public void add(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer showCustomerEdit(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        customerRepository.isDelete(id);
    }

    @Override
    public void edit(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> showMaleCustomer() {
        return customerRepository.findAllByFlagDeleteFalseAndGenderIsTrue();
    }

    @Override
    public List<Customer> showFemaleCustomer() {
        return customerRepository.findAllByFlagDeleteFalseAndGenderIsFalse();
    }
}
