package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.CarDealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDealershipRepository extends JpaRepository<CarDealership, Integer> {

    List<CarDealership> findAll();
    List<CarDealership> findByNameIgnoreCaseStartingWithAndCityIgnoreCaseStartingWith(String name, String city);
}
