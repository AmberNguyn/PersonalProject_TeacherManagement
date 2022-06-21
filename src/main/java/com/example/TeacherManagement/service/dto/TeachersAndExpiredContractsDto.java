package com.example.TeacherManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachersAndExpiredContractsDto {
    private String employeeCode;
    private String firstName;
    private LocalDate endDate;
    private String contractId;
}
