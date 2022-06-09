package com.example.TeacherManagement.service.dto;

import com.example.TeacherManagement.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDto {
    //from Teacher
    private String teacherFirstName;

    private String teacherLastName;

    private LocalDate dateOfBirth;

    //from Salary
    private Integer payRate;

    private LocalDate transferredDate;

    private double coefficientMultiplier;


}
