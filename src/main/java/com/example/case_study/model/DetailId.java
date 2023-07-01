package com.example.case_study.model;

import java.io.Serializable;

public class DetailId implements Serializable {
    private Room room;
    private Facility facility;

    public DetailId(Room room, Facility facility) {
        this.room = room;
        this.facility = facility;
    }

    public DetailId() {
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
