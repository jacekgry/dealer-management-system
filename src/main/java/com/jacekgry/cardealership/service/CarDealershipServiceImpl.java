package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.repository.CarDealershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarDealershipServiceImpl implements CarDealerShipService {

    private final CarDealershipRepository carDealershipRepository;

    @Override
    public List<CarDealership> findAll() {
        return carDealershipRepository.findAll();
    }
}
