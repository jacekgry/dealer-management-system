package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Stock;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.repository.CarDealershipRepository;
import com.jacekgry.cardealership.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarDealershipServiceImpl implements CarDealershipService {

    private final CarDealershipRepository carDealershipRepository;
    private final StockRepository stockRepository;

    @Override
    public List<CarDealership> findAll() {
        return carDealershipRepository.findAll();
    }

    @Override
    public CarDealership save(CarDealership carDealership) {
        return carDealershipRepository.save(carDealership);
    }

    @Override
    public CarDealership findById(int id) {
        return carDealershipRepository.findById(id).orElseThrow(() -> new NotFoundException("Car dealership", id));
    }

    @Override
    public void deleteById(int id) {
        carDealershipRepository.deleteById(id);
    }

    @Override
    public List<Stock> getStockForCardealership(int id) {
        return stockRepository.findAllByStockIdCarDealershipId(id);
    }

    @Override
    public void saveStock(Stock stock) {
        if (stock.getAvailableNumber() > 0) {
            stockRepository.save(stock);
        }
        if (stock.getAvailableNumber() == 0) {
            stockRepository.delete(stock);
        }
    }

    @Override
    public List<CarDealership> findByNameAndCity(String name, String city) {
        return carDealershipRepository.findByNameIgnoreCaseStartingWithAndCityIgnoreCaseStartingWith(name, city);
    }
}