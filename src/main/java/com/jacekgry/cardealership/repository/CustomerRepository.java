package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByFirstNameIgnoreCaseStartingWithAndLastNameIgnoreCaseStartingWith(String firstName, String lastName);
}
