package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Customer;
import com.jacekgry.cardealership.error.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();
    Customer saveCustomer(Customer customer);
    Customer findById(int id) throws NotFoundException;
    void deleteById(int id) throws NotFoundException
            ;
    List<Customer> findByNames(String firstName, String lastName);
}
