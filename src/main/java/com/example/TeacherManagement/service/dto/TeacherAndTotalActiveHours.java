package com.example.TeacherManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAndTotalActiveHours {
    private String teacherCode;

    private double totalNumberOfActiveHours;
}
