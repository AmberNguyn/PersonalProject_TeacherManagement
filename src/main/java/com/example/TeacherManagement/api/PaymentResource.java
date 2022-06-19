package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.PaymentRequest;
import com.example.TeacherManagement.entity.Payment;
import com.example.TeacherManagement.exception.MyException;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.PaymentService;
import com.example.TeacherManagement.service.dto.PaymentDto;
import com.example.TeacherManagement.service.mapper.PaymentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private AssignmentDetailService assignmentDetailService;

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAll() {
        return ResponseEntity.ok(PaymentMapper.INSTANCE.toDtos(paymentService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("id") Integer id) {
        log.info("Searching payment id: {}", id);
        Payment payment = paymentService.findById(id)
                .orElseThrow(MyException::PaymentIdNotFound);

        return ResponseEntity.ok(PaymentMapper.INSTANCE.toDto(payment));
    }

    // ------CHECK POSTMAN ---
    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentRequest paymentRequest) {
        Payment createdPayment = paymentService.create(paymentRequest);

        return ResponseEntity.created(URI.create(PaymentResource.PATH + "/" + createdPayment.getId()))
                .body(PaymentMapper.INSTANCE.toDto(createdPayment));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        log.info("Searching payment id: {}", id);
        Payment payment = paymentService.findById(id)
                .orElseThrow(MyException::PaymentIdNotFound);

        paymentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    // CHECK POSTMAN
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> update(@PathVariable("id") Integer id,
                                             @RequestBody PaymentRequest paymentRequest) {
        Payment updatedPayment = paymentService.update(paymentRequest, id);
        return ResponseEntity.ok(PaymentMapper.INSTANCE.toDto(updatedPayment));

    }


}
