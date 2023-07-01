package com.example.case_study.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private boolean gender;

    private String citizenId;

    private String phoneNumber;

    private Date dayOfBirth;

    private String image;

    private String email;

    private boolean flagDelete;

    public Customer() {
    }

    public Customer(Integer id, String name, boolean gender, String citizenId, String phoneNumber,
                    Date dayOfBirth, String image, String email, boolean flagDelete) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.citizenId = citizenId;
        this.phoneNumber = phoneNumber;
        this.dayOfBirth = dayOfBirth;
        this.image = image;
        this.email = email;
        this.flagDelete = flagDelete;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public void setName(String name) {
        this.name = name;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }
}
