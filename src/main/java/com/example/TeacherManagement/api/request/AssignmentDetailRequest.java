package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.*;
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
public class AssignmentDetailRequest {

    private LocalDate courseStartDate;

    private LocalDate courseEndDate;

    private double expectedHours;

    private double activeHours;

    private String leaveNote;

    private Integer payRate;

    private String contractId;

    private String classId;

}
