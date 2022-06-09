package com.example.TeacherManagement.service.dto;

import com.example.TeacherManagement.entity.Gender;
import com.example.TeacherManagement.entity.Nationality;
import com.example.TeacherManagement.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private String employeeCode;

    private String firstName;

    private String middleName;

    private String lastName;

    private Gender gender;

    private Nationality nationality;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String privateEmail;

    private String teachingLevel;

    private Status status;

}
