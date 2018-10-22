package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();
    Customer saveCustomer(Customer customer);

    Optional<Customer> findById(int id);
}
