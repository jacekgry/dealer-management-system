package com.jacekgry.cardealership.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fuels")
@Data
public class Fuel {
    @Id
    private int id;

    private String name;
}
