package com.example.case_study.dto;

import com.example.case_study.model.Roles;
import com.example.case_study.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

public class EmployeeDto implements Validator {
    private Integer id;
//    @Pattern(regexp = "^[A-Z][a-z]{3}",message = "ten phai dung dinh dang.")

    private String name;

    private boolean gender;

    private String citizenId;

    private String phoneNumber;

    private String image;

    private String dayOfBirth;

    private User user;
//    @ManyToOne
//    @JoinColumn(name = "roles_id")
    private Roles roles;

    private String email;

    private boolean flagDelete;

    public EmployeeDto() {
    }

    public EmployeeDto(Integer id, String name, boolean gender, String citizenId, String phoneNumber, String image, String dayOfBirth, User user, Roles roles, String email, boolean flagDelete) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.citizenId = citizenId;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.dayOfBirth = dayOfBirth;
        this.user = user;
        this.roles = roles;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
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
        if (!employeeDto.getCitizenId().matches("^-?\\d{8}$")) {
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
    }
}
