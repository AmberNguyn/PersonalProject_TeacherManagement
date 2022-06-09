package com.example.TeacherManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingQualityManagerDto {
    private String TQMCode;
    private String firstName;
    private String lastName;
    private LocalDate startDate;
}
