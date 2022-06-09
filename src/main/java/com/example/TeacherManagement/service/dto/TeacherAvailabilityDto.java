package com.example.TeacherManagement.service.dto;

import com.example.TeacherManagement.entity.WorkingDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAvailabilityDto {
    //from Teacher
    private String teacherCode;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    //from teacher availability
    private WorkingDay workingDay;

    private boolean morningShift;

    private boolean afternoonShift;

    private boolean nightShift;
}
