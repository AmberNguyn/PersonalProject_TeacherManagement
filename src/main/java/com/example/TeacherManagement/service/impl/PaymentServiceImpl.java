package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.Payment;
import com.example.TeacherManagement.repository.PaymentRepository;
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

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findPaymentById(Integer id) {
        return paymentRepository.findById(id);
    }

    @Override
    public void deletePaymentById(Integer id) {
        paymentRepository.deleteById(id);
    }
}
