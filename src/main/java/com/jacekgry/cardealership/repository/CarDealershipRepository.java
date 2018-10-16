package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.CarDealership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarDealershipRepository extends JpaRepository<CarDealership, Integer> {

    List<CarDealership> findAll();

}
