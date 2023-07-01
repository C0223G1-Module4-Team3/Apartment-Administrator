package com.example.case_study.model;

import javax.persistence.*;

@Entity
@Table(name = "details_room")
@IdClass(DetailId.class)
public class DetailRoom {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_room",referencedColumnName = "id_room")
    private Room room;
    @Id
    @ManyToOne
    @JoinColumn(name = "facility_id",referencedColumnName ="facility_id" )
    private Facility facility;

    private Integer amount;

    public DetailRoom(Room room, Facility facility, Integer amount) {
        this.room = room;
        this.facility = facility;
        this.amount = amount;
    }

    public DetailRoom(Room room, Facility facility) {
        this.room = room;
        this.facility = facility;
    }

    public DetailRoom() {
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

}

