package com.example.case_study.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment")
@IdClass(PayMentId.class)
public class PayMent {
    @Id
    @ManyToOne
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;
    @Id
    private Integer serial;

    private boolean paided;

    public PayMent() {
    }

    public PayMent(Contract contract, Integer serial, boolean paided) {
        this.contract = contract;
        this.serial = serial;
        this.paided = paided;
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

    public boolean isPaided() {
        return paided;
    }

    public void setPaided(boolean paided) {
        this.paided = paided;
    }
}
