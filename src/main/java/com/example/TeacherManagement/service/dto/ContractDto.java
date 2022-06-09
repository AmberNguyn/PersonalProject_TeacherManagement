package com.example.TeacherManagement.service.dto;

import com.example.TeacherManagement.entity.Gender;
import com.example.TeacherManagement.entity.Nationality;
import com.example.TeacherManagement.entity.TeacherType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {
    //from Teacher
    private String firstName;

    private String lastName;

    private Gender gender;

    private String phoneNumber;

    private Nationality nationality;

    private LocalDate dateOfBirth;

    private String address;

    private TeacherType teacherType;

    //from Campus
    private String campusName;

    private String campusAddress;

    //from Contract
    private String contractType;

    private LocalDate startDate;

    private LocalDate endDate;
}
