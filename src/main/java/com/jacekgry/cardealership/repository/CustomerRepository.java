package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Override
    List<Customer> findAll();

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

    Integer deleteById(int id);
}
