package com.jacekgry.cardealership.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    private String name;

//    @Temporal(TemporalType.DATE)
//    @Column(name = "release_date")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date releaseDate;

    private String description;

    @Column(name = "engine_displacement")
    private BigDecimal engineDisplacement;

    private BigDecimal time0to100kmph;

    @Column(name = "fuel")
    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    @Column(name = "price", precision = 19, scale = 2)
    private BigDecimal price;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
//    private List<CarImg> imgs;
}
