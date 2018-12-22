package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Car;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CarService {

    List<Car> findAllByName(String name);

    List<Car> findAll();

    Integer deleteById(int id);

    Car saveCar(Car car);

    Car findById(int id);

    List<Car> findByPhraseSearch(String searchString);

    void decreasePrices(BigDecimal percentage);

    void increasePrices(BigDecimal percentage);

    List<Map.Entry<Car, Double>> getCarsRankingByRepairsPurchasesRatio();
//    void saveImg(CarImg carImg);
}
