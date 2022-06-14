package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getAll();

    Payment addPayment(Payment payment);

    Optional<Payment> findPaymentById(Integer id);

    void deletePaymentById(Integer id);
}
