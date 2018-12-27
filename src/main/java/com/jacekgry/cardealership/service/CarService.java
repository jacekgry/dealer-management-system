package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.error.NotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarService {

    List<Car> findAllByName(String name);

    List<Car> findAll();

    Integer deleteById(int id);

    Car saveCar(Car car);

    Car findById(int id) throws NotFoundException;

    List<Car> findByPhraseSearch(String searchString);

    void decreasePrices(BigDecimal percentage);

    void increasePrices(BigDecimal percentage);

    List<Map.Entry<Car, Double>> getCarsRankingByRepairsPurchasesRatio();

    List<Car> findByNameAndPrice(String name, BigDecimal minPrice, BigDecimal maxPrice);
//    void saveImg(CarImg carImg);
}
