package com.example.case_study.model;

import javax.persistence.*;

@Entity
@Table(name = "details_room")
public class DetailRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_room_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_room",referencedColumnName = "id_room")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "facility_id",referencedColumnName ="facility_id" )
    private Facility facility;

    private Integer amount;

    public DetailRoom(Integer id, Room room, Facility facility, Integer amount) {
        this.id = id;
        this.room = room;
        this.facility = facility;
        this.amount = amount;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

