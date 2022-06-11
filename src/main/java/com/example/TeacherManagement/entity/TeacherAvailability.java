package com.example.TeacherManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class TeacherAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private WorkingDay workingDay;

    @NotNull
    private boolean morningShift;

    @NotNull
    private boolean afternoonShift;

    @NotNull
    private boolean nightShift;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
