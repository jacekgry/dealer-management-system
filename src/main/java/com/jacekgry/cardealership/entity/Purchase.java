package com.jacekgry.cardealership.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private Car car;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    @NotNull
    private Customer customer;

    @ManyToOne(targetEntity = CarDealership.class)
    @JoinColumn(name = "car_dealership_id")
    @NotNull
    private CarDealership carDealership;

    @Column(name = "purchase_date")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date purchaseDate;

    @Column
    @Range(min = 0)
    @Digits(integer = 8, fraction = 2)
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
