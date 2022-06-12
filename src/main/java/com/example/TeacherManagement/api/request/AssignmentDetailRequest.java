package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDetailRequest {

    private String lesson;
    private LocalDate startDate;
    private WorkingDay workingDay;
    private boolean morningShift;
    private boolean afternoonShift;
    private boolean nightShift;
    private boolean teachingStatus; //teacher drops class
    private double activeHours; //for calculating salary
    private boolean observationStatus;

    private String teacher_code;

    private String clazz_id;

    private Integer room_number;

}
