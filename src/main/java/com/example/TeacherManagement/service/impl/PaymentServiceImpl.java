package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.api.request.PaymentRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.entity.Payment;
import com.example.TeacherManagement.exception.MyException;
import com.example.TeacherManagement.repository.PaymentRepository;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private final PaymentRepository paymentRepository;
    @Autowired
    private AssignmentDetailService assignmentDetailService;

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment create(PaymentRequest paymentRequest) {
        AssignmentDetail assignmentDetailRequest = assignmentDetailService.findById(paymentRequest.getAssignmentDetailId())
                .orElseThrow(MyException::AssignmentDetailIdNotFound);

        if (assignmentDetailRequest.getCourseEndDate().isBefore(LocalDate.now()))
        {
            throw MyException.badRequest("InvalidDayForPayment", "Invalid day for payment");
        }
        else {
            Payment createdPayment = new Payment();
            createdPayment.setPaymentType(paymentRequest.getPaymentType());
            createdPayment.setTransferredDate(paymentRequest.getTransferredDate());
            createdPayment.setTransferredAmount(paymentRequest.getTransferredAmount());
            createdPayment.setIncomeTax(paymentRequest.getIncomeTax());
            createdPayment.setIncomeBeforeTax(paymentRequest.getIncomeBeforeTax());
            createdPayment.setIsPaid(paymentRequest.getIsPaid());
            createdPayment.setAssignmentDetail(assignmentDetailRequest);

            return paymentRepository.save(createdPayment);
        }

    }

    @Override
    public Payment update(PaymentRequest paymentRequest, Integer id) {
        AssignmentDetail assignmentDetailRequest = assignmentDetailService.findById(paymentRequest.getAssignmentDetailId())
                .orElseThrow(MyException::AssignmentDetailIdNotFound);

        Payment editPayment = paymentRepository.findById(id)
                .orElseThrow(MyException::PaymentIdNotFound);

        editPayment.setTransferredAmount(paymentRequest.getTransferredAmount());
        editPayment.setTransferredDate(paymentRequest.getTransferredDate());
        editPayment.setPaymentType(paymentRequest.getPaymentType());
        editPayment.setIncomeTax(paymentRequest.getIncomeTax());
        editPayment.setIncomeBeforeTax(paymentRequest.getIncomeBeforeTax());
        editPayment.setAssignmentDetail(assignmentDetailRequest);

        return paymentRepository.save(editPayment);
    }

    @Override
    public Optional<Payment> findById(Integer id) {
        return paymentRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        paymentRepository.deleteById(id);
    }
}
