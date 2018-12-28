package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Purchase;
import com.jacekgry.cardealership.entity.Stock;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.error.NotSufficientStockException;
import com.jacekgry.cardealership.repository.PurchaseRepository;
import com.jacekgry.cardealership.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;
    private StockRepository stockRepository;

    @Override
    public List<Purchase> findCustomerPurchases(Integer id) {
        return purchaseRepository.findAllByCustomerId(id);
    }

    @Override
    public void deleteById(Integer id) {
        purchaseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(Purchase purchase) {
        Stock stock = stockRepository.
                findByStockIdCarIdAndStockIdCarDealershipId(purchase.getCar().getId(),
                        purchase.getCarDealership().getId()).
                orElseThrow(() -> new NotSufficientStockException(purchase.getCarDealership().getName(), purchase.getCar().getName()));
        if (stock.getAvailableNumber() < 1) {
            throw new NotSufficientStockException(purchase.getCarDealership().getName(), purchase.getCar().getName());
        }
        stock.setAvailableNumber(stock.getAvailableNumber() - 1);
        stockRepository.save(stock);
        purchaseRepository.save(purchase);
    }

    @Override
    public Purchase findById(int id) {
        return purchaseRepository.findById(id).orElseThrow(() -> new NotFoundException("Purchase", id));
    }

    @Override
    public List<Purchase> findBySearchCriteria(Integer carId, Integer customerId, Integer cdId, String carName, String customerFirstName, String customerLastName, String cdName) {
        return purchaseRepository.findBySearchCriteria(carId, customerId, cdId, carName, customerFirstName, customerLastName, cdName);
    }

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public List<Purchase> findByCarId(Integer id) {
        return purchaseRepository.findByCarId(id);
    }
}
