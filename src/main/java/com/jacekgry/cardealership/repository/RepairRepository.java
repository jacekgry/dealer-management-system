package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Integer> {
    List<Repair> findAllByCustomerId(int customerId);
    List<Repair> findAllByCarDealership(CarDealership carDealership);
}
