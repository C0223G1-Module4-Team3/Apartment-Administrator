package com.example.case_study.dto;

import com.example.case_study.model.RoleUser;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class AccountUserDto implements Validator {

    private String phoneNumber;

    private String password;

    private RoleUser rule;

    private boolean status;



    public AccountUserDto(String phone, String password, boolean status) {
        this.phoneNumber = phone;
        this.password = password;
        this.status = status;
    }

    public AccountUserDto(String phoneNumber, String password, RoleUser rule, boolean status) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.rule = rule;
        this.status = status;
    }

    public AccountUserDto() {
    }

    public RoleUser getRule() {
        return rule;
    }

    public void setRule(RoleUser rule) {
        this.rule = rule;
    }

    public AccountUserDto(String phone, String password) {
        this.phoneNumber = phone;
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountUserDto accountUserDto = (AccountUserDto) target;
        if (!accountUserDto.getPhoneNumber().matches("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$")){
            errors.rejectValue("phone","phone","Phone number must be 10 numbers");
        }
        if (!accountUserDto.getPassword().matches("^([A-Z])[a-zA-Z0-9]{5,}$")){
            errors.rejectValue("password","password","Password must be capitalized and at least 6 characters");
        }
    }
}
