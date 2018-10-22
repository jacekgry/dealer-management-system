package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Repair;

import java.util.List;

public interface RepairService {
    List<Repair> findCustomerRepairs(int id);
}
