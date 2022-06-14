package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.Certification;
import com.example.TeacherManagement.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDetailRequest {
    private double score;

    private LocalDate issuedDate;

    private LocalDate expiredDate;

    private String description;

    private Integer certificationId;

    private String teacherCode;
}
