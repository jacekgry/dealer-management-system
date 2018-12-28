package com.jacekgry.cardealership.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
//    private List<CarImg> imgs;
}
