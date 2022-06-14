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

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String address;

    private String privateEmail;

    private String schoolEmail;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TeacherType teacherType;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String degree; //for highest degree

    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private Nationality nationality;

}
