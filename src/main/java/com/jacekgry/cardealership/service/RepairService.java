package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Repair;

import java.util.List;

public interface RepairService {
    List<Repair> findCustomerRepairs(int id);
    List<Repair> findAllByCarDealership(CarDealership carDealership);

    List<Repair> findAll();

    void save(Repair repair);

    Repair findById(Integer id);
}
