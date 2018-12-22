package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.CarDealership;

import java.util.List;

public interface CarDealerShipService {

    List<CarDealership> findAll();
    CarDealership save(CarDealership carDealership);

    CarDealership findById(int id);
}
