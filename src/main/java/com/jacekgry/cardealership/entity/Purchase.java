package com.jacekgry.cardealership.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "purchases")
@Data
public class Purchase {

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

    @Column(name = "purchase_date")
    @Temporal(value = TemporalType.DATE)
    private Date purchaseDate;

    @Column
    private BigDecimal price;

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", car=" + car +
                ", customer=" + customer +
                ", carDealership=" + carDealership +
                ", purchaseDate=" + purchaseDate +
                ", price=" + price +
                '}';
    }
}
