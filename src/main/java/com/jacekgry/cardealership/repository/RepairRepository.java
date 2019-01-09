package com.jacekgry.cardealership.repository;

import com.jacekgry.cardealership.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Integer> {
    List<Repair> findAllByCustomerId(int customerId);
    List<Repair> findAllByCarDealershipId(int id);
    List<Repair> findAllByPurchaseId(int id);
    List<Repair> findAllByCarId(int id);

    @Query(value = "SELECT r FROM Repair r WHERE (:carId is null or r.car.id = :carId)" +
            "and (:cdId is null or r.carDealership.id = :cdId) " +
            "and (:customerId is null or r.customer.id = :customerId)" +
            "and (r.car.name like %:carName%)" +
            "and (r.customer.firstName like %:customerFirstName%)" +
            "and (r.customer.lastName like %:customerLastName%)" +
            "and (r.carDealership.name like %:cdName%)"
    )
    List<Repair> findBySearchCriteria(@Param("carId") Integer carId,
                                        @Param("customerId") Integer customerId,
                                        @Param("cdId") Integer cdId,
                                        @Param("carName") String carName,
                                        @Param("customerFirstName") String customerFirstName,
                                        @Param("customerLastName") String customerLastName,
                                        @Param("cdName") String cdName);
}
