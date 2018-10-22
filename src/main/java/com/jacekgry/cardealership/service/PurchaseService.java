package com.jacekgry.cardealership.service;


import com.jacekgry.cardealership.entity.Purchase;

import java.util.List;

public interface PurchaseService {


    List<Purchase> findCustomerPurchases(Integer id);
}
