package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Customer;
import com.jacekgry.cardealership.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Customer c = customerRepository.save(customer);
        return c;
    }

    @Override
    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }


}
