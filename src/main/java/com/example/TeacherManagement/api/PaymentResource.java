package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.PaymentRequest;
import com.example.TeacherManagement.entity.AssignmentDetail;
import com.example.TeacherManagement.entity.Payment;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.AssignmentDetailService;
import com.example.TeacherManagement.service.PaymentService;
import com.example.TeacherManagement.service.dto.PaymentDto;
import com.example.TeacherManagement.service.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(PaymentResource.PATH)
public class PaymentResource {
    public static final String PATH = "/api/payments";

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private AssignmentDetailService assignmentDetailService;

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAll()
    {
        return ResponseEntity.ok(PaymentMapper.INSTANCE.toDtos(paymentService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable("id") Integer id) throws ResourceNotFoundException{

        Payment payment = paymentService.findPaymentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment id: " + id + " not found!"));

        return ResponseEntity.ok(PaymentMapper.INSTANCE.toDto(payment));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentRequest paymentRequest) throws ResourceNotFoundException{
        AssignmentDetail assignmentDetailRequest = assignmentDetailService.findById(paymentRequest.getAssignmentDetailId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment detail id: " + paymentRequest.getAssignmentDetailId() + " not found!"));

        Payment createdPayment = paymentService.addPayment(
                new Payment(
                        null,
                        paymentRequest.getTransferredDate(),
                        paymentRequest.getTransferredAmount(),
                        paymentRequest.getIncomeTax(),
                        paymentRequest.getIncomeBeforeTax(),
                        paymentRequest.getIsPaid(),
                        paymentRequest.getPaymentType(),
                        assignmentDetailRequest
                )
        );

        return ResponseEntity.created(URI.create(PaymentResource.PATH + "/" + createdPayment.getId()))
                .body(PaymentMapper.INSTANCE.toDto(createdPayment));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        Payment payment = paymentService.findPaymentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment id: " + id + " not found"));

        paymentService.deletePaymentById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> update(@PathVariable("id") Integer id,
                                             @RequestBody PaymentRequest paymentRequest) throws ResourceNotFoundException {

        AssignmentDetail assignmentDetailRequest = assignmentDetailService.findById(paymentRequest.getAssignmentDetailId())
                .orElseThrow(() -> new ResourceNotFoundException("Assignment detail id: " + paymentRequest.getAssignmentDetailId() + " not found!"));

        Payment editPayment = paymentService.findPaymentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment id: " + id + " not found!"));

        editPayment.setTransferredAmount(paymentRequest.getTransferredAmount());
        editPayment.setTransferredDate(paymentRequest.getTransferredDate());
        editPayment.setPaymentType(paymentRequest.getPaymentType());
        editPayment.setIncomeTax(paymentRequest.getIncomeTax());
        editPayment.setIncomeBeforeTax(paymentRequest.getIncomeBeforeTax());
        editPayment.setAssignmentDetail(assignmentDetailRequest);

        Payment updatedPayment = paymentService.addPayment(editPayment);
        return ResponseEntity.ok(PaymentMapper.INSTANCE.toDto(updatedPayment));

    }



}
