package com.example.TeacherManagement.api;

import com.example.TeacherManagement.entity.Salary;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.SalaryService;
import com.example.TeacherManagement.service.dto.SalaryDto;
import com.example.TeacherManagement.service.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
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

    @GetMapping("/{teacherCode}/{transferredDate}")
    public ResponseEntity<SalaryDto> getSalaryBy(@PathVariable String teacherCode, @PathVariable LocalDate transferredDate) throws ResourceNotFoundException {
        Salary salary = salaryService.findSalaryByEmployeeCodeAndTransferredDate(teacherCode, transferredDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s pay slip not found")
                );
        return ResponseEntity.ok(SalaryMapper.INSTANCE.toDto(salary));

    }

    @PostMapping
    public ResponseEntity<SalaryDto> create(@RequestBody Salary salary) {
        Salary createdSalary = salaryService.addSalary(salary);
        return ResponseEntity.created(URI.create(SalaryResource.PATH + "/" + createdSalary.getTeacher().getEmployeeCode()))
                .body(SalaryMapper.INSTANCE.toDto(createdSalary));
    }

    @DeleteMapping("/{teacherCode}/{transferredDate}")
    public ResponseEntity<Void> delete(@PathVariable String teacherCode, @PathVariable LocalDate transferredDate) throws ResourceNotFoundException {
        Salary salary = salaryService.findSalaryByEmployeeCodeAndTransferredDate(teacherCode, transferredDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s pay slip not found!")
                );
        salaryService.findSalaryByEmployeeCodeAndTransferredDate(teacherCode, transferredDate);
        return ResponseEntity.noContent().build();
    }

}
