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
    private WorkingDay workingDay;

    @NotNull
    private boolean shift1;

    @NotNull
    private boolean shift2;

    @NotNull
    private boolean shift3;

    private boolean teachingStatus; //teacher drops class

    private double activeHours; //for calculating salary

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Class clazz;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
