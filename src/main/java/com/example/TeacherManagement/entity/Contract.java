package com.example.TeacherManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull
    private String contractId;

    @DateTimeFormat
    private LocalDate startDate;

    @DateTimeFormat
    private LocalDate endDate;

    @PositiveOrZero
    private Integer payRate;

    private String bank;

    private String accountNumber;

    private String branch;

    private String accountName;

    private String description;

    @NotNull
    private boolean isSigned;


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


}
