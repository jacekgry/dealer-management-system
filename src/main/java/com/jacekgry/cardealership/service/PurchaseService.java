package com.jacekgry.cardealership.service;


import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {
    List<Purchase> findCustomerPurchases(Integer id);

    List<Purchase> findByPhraseSearch(String searchString);

    void deleteById(Integer id);

    void save(Purchase purchase);

    Purchase findById(int id);

    List<Purchase> findAllByCardealership(CarDealership carDealership);

    List<Purchase> findBySearchCriteria(Integer carId, Integer customerId, Integer cdId, String carName, String customerFirstName, String customerLastName, String cdName);
}
