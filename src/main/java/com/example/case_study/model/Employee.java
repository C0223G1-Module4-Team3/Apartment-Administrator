package com.example.case_study.model;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String image;
    private boolean gender;
    private String citizenId;
    private String dayOfBirth;
    private String email;

    private String phoneNumber;
    @ManyToOne
    private AccountUser accountUser;

    @ManyToOne
    private Position position;
    private boolean flagDelete;
    public Employee() {
    }

    public Employee(Integer id, String name, String image, boolean gender, String citizenId, String dayOfBirth, String email, String phoneNumber, AccountUser accountUser, Position position, boolean flagDelete) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.gender = gender;
        this.citizenId = citizenId;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountUser = accountUser;
        this.position = position;
        this.flagDelete = flagDelete;
    }

    public Employee(Integer id, String name, String image, boolean gender, String citizenId, String dayOfBirth, String email, AccountUser accountUser, Position position, boolean flagDelete) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.gender = gender;
        this.citizenId = citizenId;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
        this.accountUser = accountUser;
        this.position = position;
        this.flagDelete = flagDelete;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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

    public void setName(String nameEmployee) {
        this.name = nameEmployee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
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
