package com.jacekgry.cardealership.contoller;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/allCars")
    public List<Car> allCars() {
        Car car = Car.builder().id(2).build();
        return carService.findAll();
    }
}
