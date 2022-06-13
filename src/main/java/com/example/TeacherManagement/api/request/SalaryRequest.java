package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryRequest {
    private Integer payRate;
    
    private LocalDate transferredDate;

    private double coefficientMultiplier;

    private String teacherCode;

}
