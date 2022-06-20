package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.PaymentRequest;
import com.example.TeacherManagement.entity.Payment;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.service.PaymentService;
import com.example.TeacherManagement.service.dto.PaymentDto;
import com.example.TeacherManagement.service.mapper.PaymentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(PaymentResource.PATH)
public class PaymentResource {
    public static final String PATH = "/api/payments";

    @Autowired
    private PaymentService paymentService;

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAll() {
        return ResponseEntity.ok(PaymentMapper.INSTANCE.toDtos(paymentService.getAll()));
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("id") Integer id) {
        log.info("Searching payment id: {}", id);

        Payment payment = paymentService.findById(id)
                .orElseThrow(BusinessLogicException::PaymentIdNotFound);
        System.out.println(payment);
        return ResponseEntity.ok(PaymentMapper.INSTANCE.toDto(payment));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentRequest paymentRequest) {
        Payment createdPayment = paymentService.create(paymentRequest);

        return ResponseEntity.created(URI.create(PaymentResource.PATH + "/" + createdPayment.getId()))
                .body(PaymentMapper.INSTANCE.toDto(createdPayment));

    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        log.info("Searching payment id: {}", id);
        Payment payment = paymentService.findById(id)
                .orElseThrow(BusinessLogicException::PaymentIdNotFound);

        paymentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> update(@PathVariable("id") Integer id,
                                             @RequestBody PaymentRequest paymentRequest) {
        Payment updatedPayment = paymentService.update(paymentRequest, id);
        return ResponseEntity.ok(PaymentMapper.INSTANCE.toDto(updatedPayment));
    }


}
