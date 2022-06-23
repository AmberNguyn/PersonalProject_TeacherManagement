package com.example.TeacherManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double score;

    @DateTimeFormat
    private LocalDate issuedDate;

    @DateTimeFormat
    private LocalDate expiredDate;

    private String description;

    @ManyToOne
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

}
