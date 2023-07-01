package com.example.case_study.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_room")
    private Integer id;

    private Integer floor;

    private Double price;

    private Integer maxPeople;

    private Integer bedroom;

    private Double area;

    private boolean maintenance;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "details_room",
            joinColumns = { @JoinColumn(name = "id_room")},
            inverseJoinColumns = {@JoinColumn(name = "facility_id")}
    )
    private List<Facility> facilityList = new ArrayList<>();

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

    public Room(Integer id, Integer floor, Double price, Integer maxPeople, Integer bedroom,
                Double area, boolean maintenance, List<Facility> facilityList) {
        this.id = id;
        this.floor = floor;
        this.price = price;
        this.maxPeople = maxPeople;
        this.bedroom = bedroom;
        this.area = area;
        this.maintenance = maintenance;
        this.facilityList = facilityList;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public List<Facility> getFacilityList() {
        return facilityList;
    }

    public void setFacilityList(List<Facility> facilityList) {
        this.facilityList = facilityList;
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
