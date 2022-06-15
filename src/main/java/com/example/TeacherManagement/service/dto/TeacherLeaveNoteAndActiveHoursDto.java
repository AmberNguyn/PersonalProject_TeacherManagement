package com.example.TeacherManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherLeaveNoteAndActiveHoursDto {
    private String firstName;
    private String lastName;

    private String leaveNote;
    private double expectedHours;
    private double activeHours;
}
