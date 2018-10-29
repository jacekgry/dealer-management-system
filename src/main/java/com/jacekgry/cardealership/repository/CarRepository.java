package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> findByName(String name);
    List<Car> findAll();
    Integer deleteById(int id);
}
