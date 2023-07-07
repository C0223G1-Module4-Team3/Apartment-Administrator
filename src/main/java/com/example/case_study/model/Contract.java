package com.example.case_study.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id_room")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    //    @Column(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "kind_contract_id", referencedColumnName = "id")
    private KindContract kindContract;

    private Integer period;

    private Date date_start;

    private boolean directorConfirm;

    private boolean managerConfirm;

    private boolean flagDelete;

    public Contract() {
    }

    public Contract(Integer id, Room room, Customer customer, Employee employee, KindContract kindContract,
                    Integer period, Date date_start, boolean directorConfirm, boolean managerConfirm, boolean flagDelete) {
        this.id = id;
        this.room = room;
        this.customer = customer;
        this.employee = employee;
        this.kindContract = kindContract;
        this.period = period;
        this.date_start = date_start;
        this.directorConfirm = directorConfirm;
        this.managerConfirm = managerConfirm;
        this.flagDelete = flagDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public KindContract getKindContract() {
        return kindContract;
    }

    public void setKindContract(KindContract kindContract) {
        this.kindContract = kindContract;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Date getDate_start() {
        return date_start;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public boolean isDirectorConfirm() {
        return directorConfirm;
    }

    public void setDirectorConfirm(boolean directorConfirm) {
        this.directorConfirm = directorConfirm;
    }

    public boolean isManagerConfirm() {
        return managerConfirm;
    }

    public void setManagerConfirm(boolean managerConfirm) {
        this.managerConfirm = managerConfirm;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }
}
