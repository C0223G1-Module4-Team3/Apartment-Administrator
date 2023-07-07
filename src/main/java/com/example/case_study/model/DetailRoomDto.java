package com.example.case_study.model;

public class DetailRoomDto {
    private Integer roomId;
    private Integer facilityId;
    private Integer amount;

    public DetailRoomDto() {
    }

    public DetailRoomDto(Integer roomId, Integer facilityId, Integer amount) {
        this.roomId = roomId;
        this.facilityId = facilityId;
        this.amount = amount;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
