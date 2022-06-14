package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {

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

    private String teacherCode;
}
