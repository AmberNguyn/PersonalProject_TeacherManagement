package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.api.request.PaymentRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.entity.Payment;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.repository.PaymentRepository;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(BusinessLogicException::AssignmentDetailIdNotFound);

        Payment createdPayment = new Payment();
        createdPayment.setPaymentType(paymentRequest.getPaymentType());
        createdPayment.setTransferredDate(assignmentDetailRequest.getCourseEndDate().plusDays(15));
        createdPayment.setIncomeBeforeTax(assignmentDetailService.findIncomeBeforeTaxByAssignmentDetailIdUsingExpectedHours(assignmentDetailRequest.getId()));
        createdPayment.setIncomeTax(assignmentDetailService.findIncomeTaxByAssignmentDetailIdUsingExpectedHours(assignmentDetailRequest.getId()));
        createdPayment.setTransferredAmount(assignmentDetailService.findTransferredAmountByAssignmentDetailIdUsingExpected(assignmentDetailRequest.getId()));
        createdPayment.setIsPaid(false);
        createdPayment.setAssignmentDetail(assignmentDetailRequest);
        createdPayment.setIsManuallyUpdated(false);

        return paymentRepository.save(createdPayment);


    }

    @Override
    public Payment update(PaymentRequest paymentRequest, Integer id) {
        AssignmentDetail assignmentDetailRequest = assignmentDetailService.findById(paymentRequest.getAssignmentDetailId())
                .orElseThrow(BusinessLogicException::AssignmentDetailIdNotFound);

        Payment editPayment = paymentRepository.findById(id)
                .orElseThrow(BusinessLogicException::PaymentIdNotFound);

        if (!editPayment.getIsPaid()){
            editPayment.setTransferredDate(paymentRequest.getTransferredDate());
            editPayment.setPaymentType(paymentRequest.getPaymentType());
            editPayment.setIncomeBeforeTax(assignmentDetailService.findIncomeBeforeTaxByAssignmentDetailIdUsingActiveHours(assignmentDetailRequest.getId()));
            editPayment.setIncomeTax(assignmentDetailService.findTaxByAssignmentDetailIdUsingActiveHours(assignmentDetailRequest.getId()));
            editPayment.setTransferredAmount(assignmentDetailService.findTransferredAmountByAssignmentDetailIdUsingActiveHours(assignmentDetailRequest.getId()));
            editPayment.setIsPaid(true);
            editPayment.setIsManuallyUpdated(true);
            return paymentRepository.save(editPayment);
        } else {
            throw BusinessLogicException.badRequest("ConstraintValidationAlreadyPaid", "Constraint validation. Already paid");
        }

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
