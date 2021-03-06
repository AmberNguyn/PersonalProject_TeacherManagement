package com.example.TeacherManagement.service.dto;

import com.example.TeacherManagement.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    //from payment
    private Integer id;
    private LocalDate transferredDate;
    private double transferredAmount;
    private double incomeTax;
    private double incomeBeforeTax;
    private PaymentType paymentType;
    private Boolean isPaid;
    private Boolean isManuallyUpdated;

    //from teacher
    private String employeeCode;
    private String firstName;
    private String lastName;

    //from Contract
    private String accountNumber;
    private String bank;
    private String accountName;

    //from AssignmentDetail
    private double activeHours;


}
