package com.example.TeacherManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzHaveNotBeenAssignedDto {
    private Integer id;
    private String classId;
    private LocalDate startDate;
}
