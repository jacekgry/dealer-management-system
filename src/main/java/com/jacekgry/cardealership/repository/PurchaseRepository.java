package com.jacekgry.cardealership.repository;


import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAllByCustomerId(int customerId);

    List<Purchase> findAllByCarDealership(CarDealership carDealership);

    @Query(value = "SELECT p FROM Purchase p WHERE (:carId is null or p.car.id = :carId)" +
            "and (:cdId is null or p.carDealership.id = :cdId) " +
            "and (:customerId is null or p.customer.id = :customerId)" +
            "and (p.car.name like %:carName%)" +
            "and (p.customer.firstName like %:customerFirstName%)" +
            "and (p.customer.lastName like %:customerLastName%)" +
            "and (p.carDealership.name like %:cdName%)"
    )
    List<Purchase> findBySearchCriteria(@Param("carId") Integer carId,
                                        @Param("customerId") Integer customerId,
                                        @Param("cdId") Integer cdId,
                                        @Param("carName") String carName,
                                        @Param("customerFirstName") String customerFirstName,
                                        @Param("customerLastName") String customerLastName,
                                        @Param("cdName") String cdName);

    List<Purchase> findByCarId(Integer id);
}

