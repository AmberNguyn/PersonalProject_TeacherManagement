package com.example.TeacherManagement.service;

import com.example.TeacherManagement.api.request.PaymentRequest;
import com.example.TeacherManagement.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getAll();

    Payment create(Payment payment);
    Payment create(PaymentRequest paymentRequest);

    Payment update(PaymentRequest paymentRequest, Integer id);

    Optional<Payment> findById(Integer id);

    void deleteById(Integer id);
}
