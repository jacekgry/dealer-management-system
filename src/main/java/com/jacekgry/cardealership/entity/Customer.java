package com.jacekgry.cardealership.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
