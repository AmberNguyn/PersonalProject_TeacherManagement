package com.example.TeacherManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer payRate;

    private String bank;

    private String accountNumber;

    private String branch;

    private String accountName;

    private String description;

    private boolean isSigned;


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


}
