package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Repair;

import java.util.List;

public interface RepairService {
    List<Repair> findAll();

    void save(Repair repair);

    Repair findById(Integer id);

    List<Repair> findBySearchCriteria(Integer carId, Integer customerId, Integer cdId, String carName, String customerFirstName, String customerLastName, String cdName, String state);

    void deleteById(Integer id);

    String repairsAssociatedWithCar(int id);

    String repairsAssociatedWithCarDealership(int id);

    String repairsAssociatedWithCustomer(int id);

    String repairsAssociatedWithPurchase(int id);

}
