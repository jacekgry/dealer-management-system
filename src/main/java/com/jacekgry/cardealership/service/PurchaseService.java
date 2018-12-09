package com.jacekgry.cardealership.service;


import com.jacekgry.cardealership.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findCustomerPurchases(Integer id);
    List<Purchase> findByPhraseSearch(String searchString);
    void deleteById(Integer id);
    void save(Purchase purchase);
}
