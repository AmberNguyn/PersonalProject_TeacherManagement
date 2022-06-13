package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.WorkingDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAvailabilityRequest {
    private LocalDate startDate;

    private WorkingDay workingDay;

    private boolean morningShift;

    private boolean afternoonShift;

    private boolean nightShift;

    private String teacherCode;
}
