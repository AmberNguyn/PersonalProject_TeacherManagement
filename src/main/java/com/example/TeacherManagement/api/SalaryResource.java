package com.example.TeacherManagement.api;

import com.example.TeacherManagement.service.SalaryService;
import com.example.TeacherManagement.service.dto.SalaryDto;
import com.example.TeacherManagement.service.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(SalaryResource.PATH)
public class SalaryResource {
    @Autowired
    private SalaryService salaryService;

    public static final String PATH = "/api/salary";

    @GetMapping
    public ResponseEntity<List<SalaryDto>> getAll() {
        return ResponseEntity.ok(SalaryMapper.INSTANCE.toDtos(salaryService.getAll()));
    }
}
