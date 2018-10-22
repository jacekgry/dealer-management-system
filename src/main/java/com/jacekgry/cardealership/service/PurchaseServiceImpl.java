package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Purchase;
import com.jacekgry.cardealership.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findCustomerPurchases(Integer id) {
        return purchaseRepository.findAllByCustomerId(id);
    }
}
