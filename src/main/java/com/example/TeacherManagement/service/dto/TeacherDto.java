package com.example.TeacherManagement.service.dto;

import com.example.TeacherManagement.entity.Gender;
import com.example.TeacherManagement.entity.Nationality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private Integer id;
    private String employeeCode;
    private String fullName;
    private String firstName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String privateEmail;

    // from nationality
    private String nationality;
}
