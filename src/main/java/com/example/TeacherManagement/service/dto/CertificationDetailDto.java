package com.example.TeacherManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDetailDto {
    //from certificationDetail
    private double score;
    private LocalDate issuedDate;
    private LocalDate expiredDate;
}
