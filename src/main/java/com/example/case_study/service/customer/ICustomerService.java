package com.example.case_study.service.customer;

import com.example.case_study.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    Page<Customer> display(Pageable pageable);
    List<Customer> displayListCustomer();
    Page<Customer> search(Pageable pageable, String name,String phoneNumber, String citizenId);

    void add(Customer customer);

    Customer showCustomerEdit(int id);

    void delete(int id);

    void edit(Customer customer);
}
