package com.jacekgry.cardealership.service;


import com.jacekgry.cardealership.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findCustomerPurchases(Integer id);

    void deleteById(Integer id);

    void save(Purchase purchase);

    Purchase findById(int id);

    List<Purchase> findBySearchCriteria(Integer carId, Integer customerId, Integer cdId, String carName, String customerFirstName, String customerLastName, String cdName);

    List<Purchase> findAll();

    List<Purchase> findByCarId(Integer id);

    String purchasesAssociatedWithCar(int id);

    String purchasesAssociatedWithCarDealership(int id);

    String purchasesAssociatedWithCustomer(int id);
}
