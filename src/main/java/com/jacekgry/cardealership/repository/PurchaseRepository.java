package com.jacekgry.cardealership.repository;


import com.jacekgry.cardealership.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAllByCustomerId(int customerId);
    List<Purchase> findAll();
    void deleteById(Integer id);
}

