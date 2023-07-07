package com.example.case_study.model;

import javax.persistence.*;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String name;


    public Position(Integer id, String position) {
        this.id = id;
        this.name = position;
    }

    public Position() {
    }

    public Position(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String position) {
        this.name = position;
    }
}
