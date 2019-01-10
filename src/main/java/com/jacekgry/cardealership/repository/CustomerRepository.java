package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByFirstNameIgnoreCaseStartingWithAndLastNameIgnoreCaseStartingWith(String firstName, String lastName);
}
