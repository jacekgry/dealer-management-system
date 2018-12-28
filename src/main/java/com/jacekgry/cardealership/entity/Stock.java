package com.jacekgry.cardealership.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "stock")
@Data
public class Stock {
    @EmbeddedId
    private StockId stockId;

    @Column(name = "available_number")
    @NotNull
    @Range(min = 0)
    private Integer availableNumber;

    public Stock(CarDealership carDealership){
        this.stockId = new StockId();
        this.stockId.setCarDealership(carDealership);
    }

    public Integer getCdId() {
        return stockId.getCarDealership().getId();
    }
}

@Embeddable
@Data
class StockId implements Serializable {
    @ManyToOne(targetEntity = CarDealership.class)
    @JoinColumn(name = "car_dealership_id")
    private CarDealership carDealership;
    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(name = "car_id")
    private Car car;
}
