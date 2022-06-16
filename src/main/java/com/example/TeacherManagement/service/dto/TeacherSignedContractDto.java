package com.example.TeacherManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherSignedContractDto {
    private String teacherCode;
    private String firstName;
    private String lastName;

    private Boolean isSigned;
}
