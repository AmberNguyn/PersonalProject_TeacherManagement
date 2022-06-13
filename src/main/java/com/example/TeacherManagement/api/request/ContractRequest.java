package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.ContractType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {

    private String contractId;
    private ContractType contractType;
    private LocalDate startDate;
    private LocalDate endDate;


    private String teacherCode;

    private String campusCode;
}
