package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Repair;

import java.util.List;

public interface RepairService {
    List<Repair> findAll();

    void save(Repair repair);

    Repair findById(Integer id);

    List<Repair> findBySearchCriteria(Integer carId, Integer customerId, Integer cdId, String carName, String customerFirstName, String customerLastName, String cdName);

    void deleteById(Integer id);
}
