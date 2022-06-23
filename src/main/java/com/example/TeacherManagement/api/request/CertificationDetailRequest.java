package com.example.TeacherManagement.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDetailRequest {
    private double score;

    @DateTimeFormat
    private LocalDate issuedDate;

    @DateTimeFormat
    private LocalDate expiredDate;

    private String description;

    private Integer certificationId;

    private String teacherCode;
}
