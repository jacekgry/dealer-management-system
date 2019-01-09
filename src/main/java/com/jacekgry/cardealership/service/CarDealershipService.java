package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Stock;

import java.util.List;
import java.util.Optional;

public interface CarDealershipService {

    List<CarDealership> findAll();
    CarDealership save(CarDealership carDealership);
    CarDealership findById(int id);
    void deleteById(int id);
    List<Stock> getStockForCardealership(int id);
    void saveStock(Stock stock);
    List<CarDealership> findByNameAndCity(String name, String city);
}