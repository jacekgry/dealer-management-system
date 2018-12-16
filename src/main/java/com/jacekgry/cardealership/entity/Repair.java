package com.jacekgry.cardealership.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table (name = "repairs")
@Data
public class Repair {

    @Id
    private int id;

    @ManyToOne(targetEntity = Car.class)
    private Car car;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(targetEntity = CarDealership.class)
    @JoinColumn(name = "car_dealership_id")
    private CarDealership carDealership;

    @Column(name = "submission_date")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submissionDate;

    @Column(name = "end_date")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column
    private BigDecimal price;

    @Column(name="customers_description")
    private String customersDescription;

    @Column(name = "mechanics_description")
    private String mechanicsDescription;

}
