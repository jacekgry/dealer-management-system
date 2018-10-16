package com.jacekgry.cardealership.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String street;

    @Column(name = "building_number")
    private String buildingNumber;

    @Column(name = "postal_code")
    private String postalCode;

    @Column
    private String phone;

}
