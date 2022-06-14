package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.Gender;
import com.example.TeacherManagement.entity.Nationality;
import com.example.TeacherManagement.entity.TeacherType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {
    private String employeeCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String address;
    private String privateEmail;
    private String schoolEmail;
    private TeacherType teacherType;
    private Gender gender;
    private String degree; //for highest degree

    //from nationality
    private Integer nationalityId;

}
