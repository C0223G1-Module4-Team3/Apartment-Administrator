package com.example.case_study.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    private Integer id;

    private Integer floor;

    private Double price;
    @Column(name = "max_people")
    private Integer maxPeople;

    private Integer bedroom;

    private Double area;

    private boolean maintenance;
    public Room() {
    }

    public Room(Integer id, Integer floor, Double price, Integer maxPeople, Integer bedroom, Double area) {
        this.id = id;
        this.floor = floor;
        this.price = price;
        this.maxPeople = maxPeople;
        this.bedroom = bedroom;
        this.area = area;
    }

    public Room(Integer floor, Double price, Integer maxPeople, Integer bedroom, Double area, boolean maintenance) {
        this.floor = floor;
        this.price = price;
        this.maxPeople = maxPeople;
        this.bedroom = bedroom;
        this.area = area;
        this.maintenance = maintenance;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBedroom() {
        return bedroom;
    }

    public void setBedroom(Integer bedroom) {
        this.bedroom = bedroom;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
