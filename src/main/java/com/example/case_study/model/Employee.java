package com.example.case_study.model;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameEmployee;
    private String image;
    private boolean gender;
    private String citizenId;
    private Date dayOfBirth;
    private String email;
    @ManyToOne
    private AccountUser accountUser;
    private boolean flagDelete;
    public Employee() {
    }

    public Employee(Integer id, String nameEmployee, String image, boolean gender, String citizenId, Date dayOfBirth, String email, AccountUser accountUser, boolean flagDelete) {
        this.id = id;
        this.nameEmployee = nameEmployee;
        this.image = image;
        this.gender = gender;
        this.citizenId = citizenId;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
        this.accountUser = accountUser;
        this.flagDelete = flagDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String telephone) {
        this.email = telephone;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }
}
