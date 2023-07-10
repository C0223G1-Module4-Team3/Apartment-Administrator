package com.example.case_study.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;

public class CustomerDto implements Validator {
    private Integer id;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^[\\p{Lu}][\\p{Ll}]{1,8}(\\s([\\p{Lu}]|[\\p{Lu}][\\p{Ll}]{1,10})){0,5}$",
            message = "Tên phải bắt đầu bằng chữ hoa và theo sau là chữ thường")
    private String name;

    private boolean gender;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^[0-9]{12}$", message = "cmnd phải đủ 12 số")
    private String citizenId;
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^0[0-9]{9}$", message = "số điện thoại phải bắt đầu bằng 0 và đủ 10 số ")
    private String phoneNumber;
    @NotBlank(message = "Không được để trống")
    private String dayOfBirth;

    private String image;
    @NotBlank(message = "Không được để trống")
    @Email(message = "Email phải đúng định dạng")
    private String email;

    private boolean flagDelete;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String name, boolean gender, String citizenId, String phoneNumber, String dayOfBirth,
                       String image, String email, boolean flagDelete) {
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

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto customerDto= (CustomerDto) target;
        Period age = Period.between(LocalDate.parse(customerDto.getDayOfBirth()), LocalDate.now()); // Tính độ tuổi

        int years = age.getYears(); // Lấy số năm từ kết quả Period

        if (years < 18) {
            errors.rejectValue("dayOfBirth","dayOfBirth","You are not old enough to work");
        }
    }
}
