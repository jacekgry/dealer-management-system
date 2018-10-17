package com.jacekgry.cardealership.repository;


import com.jacekgry.cardealership.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {



}
