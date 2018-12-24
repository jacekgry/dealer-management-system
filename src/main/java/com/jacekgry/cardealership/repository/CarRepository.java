package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.math.BigDecimal;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByName(String name);
    List<Car> findAll();
    Integer deleteById(int id);

    @Procedure(procedureName = "decrease_prices")
    void decreasePrices(BigDecimal percentage);

    @Procedure(procedureName = "increase_prices")
    void increasePrices(BigDecimal percentage);

//    @Procedure(procedureName = "get_cars_sorted_by_repairs_purchases_ratio")
    @Query(nativeQuery = true, value = "SELECT get_cars_sorted_by_repairs_purchases_ratio()")
    String carsByRepairsPurchasesRatio();

    List<Car> findByNameIgnoreCaseStartingWithAndPriceGreaterThanAndPriceLessThan(String name, BigDecimal minPrice, BigDecimal maxPrice);

}
