package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.Campus;
import com.example.TeacherManagement.entity.ContractType;
import com.example.TeacherManagement.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractRequest {

    private String contractId;
    private ContractType contractType;
    private LocalDate startDate;
    private LocalDate endDate;


    private String teacher_code;

    private String campus_code;
}
