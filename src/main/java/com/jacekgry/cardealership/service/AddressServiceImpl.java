package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

}
