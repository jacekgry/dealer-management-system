package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Customer;
import com.jacekgry.cardealership.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
