package com.jacekgry.cardealership.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @Size(min=2, max=50)
    private String firstName;

    @Column(name = "last_name")
    @Size(min=2, max=50)
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "^[0-9]{9}$", message = "Must be 9 digits telephone number")
    @NotEmpty
    private String phone;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
