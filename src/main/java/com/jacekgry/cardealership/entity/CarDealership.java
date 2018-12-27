package com.jacekgry.cardealership.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "car_dealerships")
@Data
public class CarDealership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min=2, max=50)
    private String name;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String street;

    @NotNull
    @Column(name = "postal_code")
    private String postalCode;

    @NotNull
    @NotEmpty
    @Column(name = "building_number")
    private String buildingNumber;

    @Column
    @NotNull
    @Size(min=9, max=11)
    private String phone;

    @Email
    @NotNull
    @NotEmpty
    private String email;
}
