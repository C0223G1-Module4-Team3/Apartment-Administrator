package com.example.case_study.dto;

import com.example.case_study.model.Customer;
import com.example.case_study.model.Employee;
import com.example.case_study.model.KindContract;
import com.example.case_study.model.Room;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Min;

public class ContractCreationDto implements Validator {
    private Integer id;
    private Room room;
    private Customer customer;
    private Employee employee;
    private KindContract kindContract;
    @Min(value = 1, message = "period must > 0")
    private Integer period;
    private String date_start;
    private boolean directorConfirm;
    private boolean managerConfirm;

    private boolean flagDelete;

    public ContractCreationDto(Integer id, Room room, Customer customer, Employee employee,
                               KindContract kindContract, Integer period, String date_start,
                               boolean directorConfirm, boolean managerConfirm, boolean flagDelete) {
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

    public ContractCreationDto(Room room, Customer customer, Employee employee,
                               KindContract kindContract, Integer period, String date_start,
                               boolean directorConfirm, boolean managerConfirm, boolean flagDelete) {
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

    public ContractCreationDto() {
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

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
