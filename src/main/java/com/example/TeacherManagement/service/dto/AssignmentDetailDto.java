package com.example.TeacherManagement.service.dto;

import com.example.TeacherManagement.entity.TeacherType;
import com.example.TeacherManagement.entity.WorkingDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDetailDto {
    //from Teacher
    private String teacherCode;
    private String firstName;
    private String lastName;
    private TeacherType teacherType;
    private String phoneNumber;

    //from assignmentDetail
    private String lesson;
    private LocalDate startDate;
    private boolean morningShift;
    private boolean afternoonShift;
    private boolean nightShift;
    private WorkingDay weekday;

    //from Room
    private Integer roomNumber;

    //From class
    private String classId;
    private String courseBook;
}
