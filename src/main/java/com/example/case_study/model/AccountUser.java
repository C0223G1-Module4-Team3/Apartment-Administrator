package com.example.case_study.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Entity
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(nullable = false)
    private String phone;
    @JsonIgnore
    private String password;
    @ManyToOne
    private RoleUser roleUser;

    public AccountUser() {
    }

    public AccountUser(Integer id, String email, String passwords, RoleUser roleUser) {
        this.id = id;
        this.phone = email;
        this.password = passwords;
        this.roleUser = roleUser;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String email) {
        this.phone = email;
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
