package com.jacekgry.cardealership.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "car_dealerships")
@Data
public class CarDealership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min=2, max=50)
    private String name;

    @NotEmpty
    private String city;

    @NotEmpty
    private String street;

    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$", message = "Must be valid postal code in format xy-uvw")
    @Column(name = "postal_code")
    private String postalCode;

    @NotNull
    @NotEmpty
    @Column(name = "building_number")
    private String buildingNumber;

    @Column
    @Pattern(regexp = "^[0-9]{9}", message = "Must be 9-digits telephone number")
    private String phone;

    @Email
    private String email;
}
