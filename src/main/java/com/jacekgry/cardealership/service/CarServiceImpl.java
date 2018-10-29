package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.entity.Fuel;
import com.jacekgry.cardealership.repository.CarRepository;
import com.jacekgry.cardealership.repository.FuelRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    private FuelRepository fuelRepository;

    @Override
    public List<Car> findAllByName(String name) {
        return carRepository.findByName(name);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Integer deleteById(int id) {
        return carRepository.deleteById(id);
    }

    @Override
    public List<Fuel> findAllFuels() {
        return fuelRepository.findAll();
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

}
