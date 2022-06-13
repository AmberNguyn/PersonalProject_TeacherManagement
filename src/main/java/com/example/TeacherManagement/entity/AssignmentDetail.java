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
public class AssignmentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String lesson;

    @NotNull
    private LocalDate startDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private WorkingDay workingDay;

    @NotNull
    private boolean morningShift;

    @NotNull
    private boolean afternoonShift;

    @NotNull
    private boolean nightShift;

    private boolean teachingStatus; //teacher drops class
    private double activeHours; //for calculating salary
    private boolean observationStatus;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

}
