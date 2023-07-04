package com.example.case_study.model;

import java.io.Serializable;

public class PayMentId implements Serializable {
    private Contract contract;
    private Integer serial;

    public PayMentId(Contract contract, Integer serial) {
        this.contract = contract;
        this.serial = serial;
    }

    public PayMentId() {
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }
}
