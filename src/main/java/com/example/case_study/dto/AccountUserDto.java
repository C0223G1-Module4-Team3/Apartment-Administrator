package com.example.case_study.dto;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;


public class AccountUserDto implements Validator {
    @NotBlank(message = "Phone Number is required")
    @Min(value = 10, message = "Phone number must be 10 numbers")
    private String phone;
    @NotBlank(message = "Password is required" )
    @Min(value = 6,message = "Password must be 6 characters")
    private String password;

    public AccountUserDto() {
    }

    public AccountUserDto(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
        return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
        return null;
    }

    @Override
    public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }

    @Override
    public ExecutableValidator forExecutables() {
        return null;
    }
}
