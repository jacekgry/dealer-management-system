package com.jacekgry.cardealership.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 50)
    private String name;

    @NotEmpty
    private String description;

    @Column(name = "engine_displacement")
    @NotNull
    @Digits(integer = 2, fraction = 2)
    private BigDecimal engineDisplacement;

    @NotNull
    @Digits(integer = 2, fraction = 2)
    private BigDecimal time0to100kmph;

    @Column(name = "fuel")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Fuel fuel;

    @Column(name = "price", precision = 19, scale = 2)
    @NotNull
    @Range(min = 0)
    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;
}
