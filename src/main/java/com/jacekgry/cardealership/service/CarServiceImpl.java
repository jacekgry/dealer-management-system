package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Car;
import com.jacekgry.cardealership.entity.Stock;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.repository.CarRepository;
import com.jacekgry.cardealership.repository.StockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private StockRepository stockRepository;
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
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car", id));
    }

    @Override
    public void decreasePrices(BigDecimal percentage) {
        carRepository.decreasePrices(percentage);
    }

    @Override
    public void increasePrices(BigDecimal percentage) {
        carRepository.increasePrices(percentage);
    }

    @Override
    public List<Map.Entry<Car, Double>> getCarsRankingByRepairsPurchasesRatio() {
        String carsRanking = carRepository.carsByRepairsPurchasesRatio();
        List<Map.Entry<Car, Double>> carsRatiosList = Arrays.stream(carsRanking.split(",")).map(entry -> {
            String[] carRatioPair = entry.split(";");
            Car car = carRepository.findById(Integer.parseInt(carRatioPair[0])).get();
            return new AbstractMap.SimpleEntry<Car, Double>(car, Double.parseDouble(carRatioPair[1]));
        }).collect(toList());
        return carsRatiosList;
    }

    @Override
    public List<Car> findByNameAndPrice(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        return carRepository.findByNameIgnoreCaseStartingWithAndPriceGreaterThanAndPriceLessThan(name, minPrice, maxPrice);
    }

    @Override
    public List<Car> findCarsNotYetInStock(int id) {
        List<Car> cars = carRepository.findAll();
        List<Car> carsAlreadyInStock = stockRepository.findAllByStockIdCarDealershipId(id).stream().map(Stock::getCar).collect(toList());
        cars = cars.stream().filter(car -> !carsAlreadyInStock.contains(car)).collect(toList());
        return cars;
    }

    @Override
    public List<Integer> carsInCarsDealership(int id) {
        return stockRepository
                .findAllByStockIdCarDealershipId(id)
                .stream()
                .mapToInt(s -> s.getCar().getId())
                .boxed()
                .collect(toList());
    }
}
