package com.example.case_study.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Entity
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String phoneNumber;
    @JsonIgnore
    private String password;
    @ManyToOne
    private RoleUser roleUser;

    private boolean status;

    public AccountUser() {
    }

    public AccountUser(Integer id, String phoneNumber, String password, RoleUser roleUser, boolean flagDatele) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roleUser = roleUser;
        this.status = flagDatele;
    }

    public AccountUser(String phone, String password) {
        this.phoneNumber = phone;
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean flagDatele) {
        this.status = flagDatele;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String email) {
        this.phoneNumber = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwords) {
        this.password = passwords;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }


}
