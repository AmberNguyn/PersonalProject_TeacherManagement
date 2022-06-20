package com.example.TeacherManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzDto {
    private Integer id;
    private String classId;
    private Integer numberOfStudent;
    private LocalDate startDate;
    private LocalDate endDate;
    private String courseBook;
}
