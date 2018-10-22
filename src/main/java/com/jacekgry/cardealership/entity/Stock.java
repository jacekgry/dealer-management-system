package com.jacekgry.cardealership.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stock")
@Data
public class Stock {

//    @Id
//    @Column(name = "stock_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Car.class)
//    @JoinColumn(name = "car_id")
//    private Car car;
//
//    @ManyToOne(cascade = CascadeType.ALL, targetEntity = CarDealership.class)
//    @JoinColumn(name = "car_dealership_id")
//    private CarDealership carDealership;
    @EmbeddedId
    private StockId id;

    @Column(name = "available_number")
    private Integer availableNumber;

}

@Embeddable
@Data
class StockId implements Serializable {
    @Column(name = "car_dealership_id" )
    private int carDealershipId;
    @Column(name = "car_id")
    private int carId;
}
