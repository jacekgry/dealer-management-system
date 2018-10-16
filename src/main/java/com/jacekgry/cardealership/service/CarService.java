package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> findAllByName(String name);
    List<Car> findAll();
}
