package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
//    private CarImgRepository carImgRepository;

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
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car findById(int id) {
        return carRepository.findById(id).get();
    }

    @Override
    public List<Car> findByPhraseSearch(String searchString) {
        List<Car> cars = carRepository.findAll();
        if (searchString == null) return cars;
        final String searchStringLowerCaseTrimmed = searchString.toLowerCase().trim();


        cars = cars.stream().filter(car -> car.getName().toLowerCase().trim().contains(searchStringLowerCaseTrimmed)).collect(Collectors.toList());
        return cars;
    }

    @Override
    public void decreasePrices(BigDecimal percentage) {
        carRepository.decreasePrices(percentage);
    }

    @Override
    public void increasePrices(BigDecimal percentage) {
        carRepository.increasePrices(percentage);
    }

//    @Override
//    public void saveImg(CarImg carImg) {
//        carImgRepository.save(carImg);
//    }

}
