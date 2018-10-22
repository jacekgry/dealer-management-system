package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    List<Stock> findAllByIdCarDealershipId(int carDealerShipId);
    List<Stock> findAllByIdCarId(int carId);
    Optional<Stock> findByIdCarIdAndIdCarDealershipId(int carId, int carDealershipId);

}
