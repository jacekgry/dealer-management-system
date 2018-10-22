package com.jacekgry.cardealership.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table (name = "repairs")
public class Repair {

    @Id
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Car.class)
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = CarDealership.class)
    @JoinColumn(name = "car_dealership_id")
    private CarDealership carDealership;

    @Column(name = "submission_date")
    @Temporal(value = TemporalType.DATE)
    private Date submissionDate;

    @Column(name = "end_date")
    @Temporal(value = TemporalType.DATE)
    private Date endDate;

    @Column
    private BigDecimal price;

    @Column(name="customers_description")
    private String customersDescription;

    @Column(name = "mechanics_description")
    private String mechanicsDescription;

}
