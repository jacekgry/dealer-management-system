package com.jacekgry.cardealership.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "car_dealerships")
@Data
public class CarDealership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String city;

    private String street;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column
    private String phone;




}
