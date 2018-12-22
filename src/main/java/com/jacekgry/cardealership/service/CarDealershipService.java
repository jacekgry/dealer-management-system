package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Stock;

import java.util.List;

public interface CarDealershipService {

    List<CarDealership> findAll();
    CarDealership save(CarDealership carDealership);

    CarDealership findById(int id);
    void deleteById(int id);
    List<Stock> getStockForCardealership(int id);
    Stock findStockByCardealershipIdAndCarId(int carDealershipId, int carId);
    void saveStock(Stock stock);

}