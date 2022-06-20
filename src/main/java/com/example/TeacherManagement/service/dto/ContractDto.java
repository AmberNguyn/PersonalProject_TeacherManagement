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
    //from Contract
    private Integer id;
    private String contractId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer payRate;
    private String accountNumber;
    private String bank;
    private String accountName;
    private boolean isSigned;

    //from Teacher
    private String employeeCode;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;
    private TeacherType teacherType;

    //from nationality
    private String nationality;
}
