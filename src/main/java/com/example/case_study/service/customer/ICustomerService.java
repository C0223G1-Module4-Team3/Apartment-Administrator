package com.example.case_study.service.customer;

import com.example.case_study.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    Page<Customer> display(Pageable pageable);
    Page<Customer> search(Pageable pageable, String name,String phoneNumber, String citizenId);

    void add(Customer customer);

    Customer showCustomerEdit(int id);

    void delete(int id);

    void edit(Customer customer);
}
