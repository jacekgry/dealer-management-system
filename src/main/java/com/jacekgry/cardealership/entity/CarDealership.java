package com.jacekgry.cardealership.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "car_dealerships")
@Data
public class CarDealership {

    @Column(name = "car_dealership_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "name")
    private String name;
}
