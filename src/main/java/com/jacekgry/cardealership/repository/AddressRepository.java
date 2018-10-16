package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
