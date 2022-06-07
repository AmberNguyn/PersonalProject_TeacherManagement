package com.example.TeacherManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TrainingQualityManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull
    private String employeeCode;

    @NotNull
    @Column(length = 30)
    private String firstName;

    @Column(length = 30)
    private String middleName;

    @Column(length = 30)
    @NotNull
    private String lastName;

    private LocalDate startDate;
}
