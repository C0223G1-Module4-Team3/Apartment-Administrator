package com.example.case_study.dto;

import com.example.case_study.model.Position;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Period;

public class EmployeeDto implements Validator {
    private Integer id;
//    @Pattern(regexp = "^[A-Z][a-z]{3}",message = "ten phai dung dinh dang.")

    private String name;

    private boolean gender;

    private String citizenId;

    private String phoneNumber;

    private String image;

    private String dayOfBirth;

    @ManyToOne
    private Position position;

    private String email;

    private boolean flagDelete;

    public EmployeeDto() {
    }

    public EmployeeDto(Integer id, String name, boolean gender, String citizenId, String phoneNumber, String image, String dayOfBirth, Position position, String email, boolean flagDelete) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.citizenId = citizenId;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.dayOfBirth = dayOfBirth;
        this.position = position;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
        EmployeeDto employeeDto = (EmployeeDto) target;
//        tieng co dau
        if (!employeeDto.getName().matches("^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+ [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*")) {
            errors.rejectValue("name", "name", "This is not name");
        }
//        can cuoc cong dan phai du 8 so
        if (!employeeDto.getCitizenId().matches("^-?\\d{12}$")) {
            errors.rejectValue("citizenId","citizenId","This is not citizen Id");
        }
//        so dien thoai 10 so, so dau 03,05,07,08,09
        if (!employeeDto.getPhoneNumber().matches("(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})\\b")){
            errors.rejectValue("phoneNumber","phoneNumber","This is not phone Number");
        }
//        vietcao2510@gmail.com
//      if (!employeeDto.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
//          errors.rejectValue("email","email","This is not email");
//      }

        Period age = Period.between(LocalDate.parse(employeeDto.getDayOfBirth()), LocalDate.now()); // Tính độ tuổi

        int years = age.getYears(); // Lấy số năm từ kết quả Period

        if (years < 18) {
           errors.rejectValue("dayOfBirth","dayOfBirth","You are not old enough to work");
        }

    }
}
