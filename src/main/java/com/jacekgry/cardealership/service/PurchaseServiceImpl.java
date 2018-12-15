package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Purchase;
import com.jacekgry.cardealership.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findCustomerPurchases(Integer id) {
        return purchaseRepository.findAllByCustomerId(id);
    }

    @Override
    public List<Purchase> findByPhraseSearch(String searchString) {
        List<Purchase> purchases = purchaseRepository.findAll();
        if (searchString == null) return purchases;

        final String searchStringLoweCaseTrimmed = searchString.toLowerCase().trim();
        purchases = purchases.stream().filter(purchase -> {
            return purchase.getCar().getName().toLowerCase().contains(searchStringLoweCaseTrimmed) ||
                    purchase.getCustomer().toString().toLowerCase().trim().contains(searchStringLoweCaseTrimmed);
        }).collect(Collectors.toList());
        return purchases;
    }

    @Override
    public void deleteById(Integer id) {
        purchaseRepository.deleteById(id);
    }

    @Override
    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    @Override
    public Purchase findById(int id) {
        return purchaseRepository.findById(id).get();
    }
}
