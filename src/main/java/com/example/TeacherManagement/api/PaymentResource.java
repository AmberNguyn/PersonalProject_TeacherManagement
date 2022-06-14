package com.example.TeacherManagement.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PaymentResource.PATH)
public class PaymentResource {
    public static final String PATH = "/api/payments";
}
