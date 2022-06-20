package com.example.TeacherManagement.service.dto;

import com.example.TeacherManagement.entity.TeacherType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDetailDto {
    //from assignmentDetail
    private Integer id;
    private LocalDate courseStartDate;
    private LocalDate courseEndDate;
    private double expectedHours;
    private double activeHours;

    //from Teacher
    private String teacherCode;
    private String fullName;
    private TeacherType teacherType;
    private String phoneNumber;

    //From class
    private String classId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCourseHours;
}
