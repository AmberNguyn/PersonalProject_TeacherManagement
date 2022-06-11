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
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @NotNull
    private String employeeCode;

    @Column(length = 30)
    @NotNull
    private String firstName;

    @Column(length = 30)
    private String middleName;

    @Column(length = 30)
    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Nationality nationality;

    private LocalDate dateOfBirth;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

    private String privateEmail;

    @NotNull
    private String schoolEmail;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TeacherType teacherType;


    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Status status;


}
