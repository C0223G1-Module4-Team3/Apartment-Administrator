package com.example.case_study.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_number", referencedColumnName = "phone_number")
    private Employee employee;
    @Column(name = "pass_word")
    private String password;

    public User() {
    }

    public User(Employee employee, String password) {
        this.employee = employee;
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
