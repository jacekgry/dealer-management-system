package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAllByName(String name) {
        return carRepository.findByName(name);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

}
