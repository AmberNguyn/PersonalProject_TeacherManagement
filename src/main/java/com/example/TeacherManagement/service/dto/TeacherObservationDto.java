package com.example.TeacherManagement.service.dto;

import com.example.TeacherManagement.entity.TeacherType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherObservationDto {
    //from assignmentDetail -> Teacher
    private String teacherCode;
    private String firstName;
    private String lastName;
    private TeacherType teacherType;

    //from teacher observation
    private LocalDate observationDate;
    private String feedback;
    private double score;

    //from assignmentDetail
    private String lesson;

    //from TQM
    private String TQMCode;
    private String TQMFirstName;
    private String TQMLastName;
}
