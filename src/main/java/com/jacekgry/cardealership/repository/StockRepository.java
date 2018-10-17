package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {

    List<Stock> findAllByCardealershipId(int carDealerShipId);
    List<Stock> findAllByCarId(int carId);
    Optional<Stock> findByCarIdAndCarDealershipId(int carId, int carDealershipId);

}
