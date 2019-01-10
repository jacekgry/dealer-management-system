package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findAllByStockIdCarDealershipId(int carDealerShipId);
    Optional<Stock> findByStockIdCarIdAndStockIdCarDealershipId(int carId, int carDealershipId);
}
