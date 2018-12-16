package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Stock;
import com.jacekgry.cardealership.repository.CarDealershipRepository;
import com.jacekgry.cardealership.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return carDealershipRepository.findById(id).get();
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
    public Stock findStockByCardealershipIdAndCarId(int carDealershipId, int carId) {
        return stockRepository.findByStockIdCarIdAndStockIdCarDealershipId(carId, carDealershipId).get();
    }

    @Override
    public void saveStock(Stock stock) {
        stockRepository.save(stock);
    }
}