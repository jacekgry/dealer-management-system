package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.repository.CarDealershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarDealershipServiceImpl implements CarDealerShipService {

    private final CarDealershipRepository carDealershipRepository;

}
