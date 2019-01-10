package com.jacekgry.cardealership.service;

import com.jacekgry.cardealership.entity.Repair;
import com.jacekgry.cardealership.error.NotFoundException;
import com.jacekgry.cardealership.repository.RepairRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {

    private RepairRepository repairRepository;

    private final String PENDING = "pending";
    private final String ENDED = "ended";

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
        return repairRepository.findById(id).orElseThrow(() -> new NotFoundException("Repair", id));
    }

    @Override
    public List<Repair> findBySearchCriteria(Integer carId, Integer customerId, Integer cdId, String carName, String customerFirstName, String customerLastName, String cdName, String state) {
        List<Repair> repairs = repairRepository.findBySearchCriteria(carId, customerId, cdId, carName, customerFirstName, customerLastName, cdName);
        if(ENDED.equals(state)){
            repairs = repairs.stream().filter(r -> null != r.getEndDate()).collect(Collectors.toList());
        }
        else if (PENDING.equals(state)){
            repairs = repairs.stream().filter(r -> null == r.getEndDate()).collect(Collectors.toList());
        }
        return repairs;
    }

    @Override
    public void deleteById(Integer id) {
        repairRepository.deleteById(id);
    }

    @Override
    public String repairsAssociatedWithCar(int id) {
        return repairsListToStringOfIds(repairRepository.findAllByCarId(id));
    }

    @Override
    public String repairsAssociatedWithCarDealership(int id) {
        return repairsListToStringOfIds(repairRepository.findAllByCarDealershipId(id));
    }

    @Override
    public String repairsAssociatedWithCustomer(int id) {
        return repairsListToStringOfIds(repairRepository.findAllByCustomerId(id));
    }

    @Override
    public String repairsAssociatedWithPurchase(int id) {
        return repairsListToStringOfIds(repairRepository.findAllByPurchaseId(id));
    }

    private String repairsListToStringOfIds(List<Repair> repairs) {
        return "[" + repairs.stream()
                .mapToInt(Repair::getId)
                .mapToObj(Integer::toString)
                .reduce((a, b) -> String.join(",", a, b))
                .orElse("") + "]";
    }
}
