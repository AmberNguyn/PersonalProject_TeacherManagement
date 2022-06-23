package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {

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

    private String teacherCode;
}
