package com.jacekgry.cardealership.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    private String description;

    @Column(name = "engine_displacement")
    private BigDecimal engineDisplacement;

    private BigDecimal acceleration;

    @JoinColumn(name = "fuel_id")
    @ManyToOne(targetEntity = Fuel.class, cascade = CascadeType.PERSIST)
    private Fuel fuel;

    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;

}
