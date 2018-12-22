package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.CarDealership;

import java.util.List;

public interface CarDealershipService {

    List<CarDealership> findAll();
    CarDealership save(CarDealership carDealership);

    CarDealership findById(int id);
}
