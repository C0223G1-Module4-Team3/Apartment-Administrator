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


}
