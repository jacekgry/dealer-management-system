package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.CarDealership;
import com.jacekgry.cardealership.entity.Repair;
import com.jacekgry.cardealership.repository.RepairRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {

    private RepairRepository repairRepository;

    @Override
    public List<Repair> findCustomerRepairs(int id) {
        return repairRepository.findAllByCustomerId(id);
    }

    @Override
    public List<Repair> findAllByCarDealership(CarDealership carDealership) {
        return repairRepository.findAllByCarDealership(carDealership);
    }

    @Override
    public List<Repair> findAll() {
        return repairRepository.findAll();
    }

    @Override
    public void save(Repair repair) {
        repairRepository.save(repair);
    }

    @Override
    public Repair findById(Integer id) {
        return repairRepository.findById(id).get();
    }
}
