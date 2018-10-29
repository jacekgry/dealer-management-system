package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.entity.Fuel;

import java.util.List;

public interface CarService {

    List<Car> findAllByName(String name);

    List<Car> findAll();

    Integer deleteById(int id);

    List<Fuel> findAllFuels();

    Car saveCar(Car car);
}
