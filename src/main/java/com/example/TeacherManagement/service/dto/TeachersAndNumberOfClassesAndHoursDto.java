package com.example.TeacherManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachersAndNumberOfClassesAndHoursDto {
    private String employeeCode;
    private String fullName;
    private Long numberOfClasses;
    private Long totalActiveHours;
    private Integer payRate;
}
