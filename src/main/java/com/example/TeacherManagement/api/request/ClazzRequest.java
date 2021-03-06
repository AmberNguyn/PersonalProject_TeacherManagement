package com.example.TeacherManagement.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzRequest {

    private String classId;

    private Integer numberOfStudent;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer totalCourseHours;

    private String courseBook;
}
