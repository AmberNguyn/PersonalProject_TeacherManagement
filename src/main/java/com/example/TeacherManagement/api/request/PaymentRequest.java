package com.example.TeacherManagement.api.request;

import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.entity.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private LocalDate transferredDate;

    private double transferredAmount;

    private PaymentType paymentType;

    private Integer assignmentDetailId;
}
