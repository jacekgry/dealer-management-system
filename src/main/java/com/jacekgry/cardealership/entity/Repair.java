package com.jacekgry.cardealership.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "repairs")
@Data
public class Repair {

    @Id
    private int id;

    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(targetEntity = CarDealership.class)
    @JoinColumn(name = "car_dealership_id")
    private CarDealership carDealership;

    @ManyToOne(targetEntity = Purchase.class)
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Column(name = "submission_date")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date submissionDate;

    @Column(name = "end_date")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @Column
    @Range(min = 0)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;

    @Column(name = "customers_description")
    private String customersDescription;

    @Column(name = "mechanics_description")
    private String mechanicsDescription;

}
