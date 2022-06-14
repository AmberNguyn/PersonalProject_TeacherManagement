package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.SalaryRequest;
import com.example.TeacherManagement.entity.Salary;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.SalaryService;
import com.example.TeacherManagement.service.TeacherService;
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
    @Autowired
    private TeacherService teacherService;

    public static final String PATH = "/api/salaries";

    @GetMapping
    public ResponseEntity<List<SalaryDto>> getAll() {
        return ResponseEntity.ok(SalaryMapper.INSTANCE.toDtos(salaryService.getAll()));
    }

    @GetMapping("/find")
    public ResponseEntity<SalaryDto> getSalaryBy(@RequestParam("teacherCode") String teacherCode,
                                                 @RequestParam("transferredDate") LocalDate transferredDate) throws ResourceNotFoundException {
        Salary salary = salaryService.findSalaryByEmployeeCodeAndTransferredDate(teacherCode, transferredDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s pay slip not found")
                );
        return ResponseEntity.ok(SalaryMapper.INSTANCE.toDto(salary));

    }


    @PostMapping
    public ResponseEntity<SalaryDto> create(@RequestBody SalaryRequest salaryRequest) throws ResourceNotFoundException {
        Teacher teacherRequest = teacherService.findTeacherByEmployeeCode(salaryRequest.getTeacherCode())
                .orElseThrow(() -> new ResourceNotFoundException(salaryRequest.getTeacherCode() + " not found!"));


        Salary createdSalary = salaryService.addSalary(
                new Salary(
                        null,
                        salaryRequest.getPayRate(),
                        salaryRequest.getTransferredDate(),
                        salaryRequest.getCoefficientMultiplier(),
                        teacherRequest
                )
        );
        return ResponseEntity.created(URI.create(SalaryResource.PATH + "/" + createdSalary.getId()))
                .body(SalaryMapper.INSTANCE.toDto(salaryService.addSalary(createdSalary)));
    }


    @DeleteMapping("/")
    public ResponseEntity<Void> delete(@RequestParam("teacherCode") String teacherCode,
                                       @RequestParam("transferredDate") LocalDate transferredDate) throws ResourceNotFoundException {
        Salary salary = salaryService.findSalaryByEmployeeCodeAndTransferredDate(teacherCode, transferredDate)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s pay slip not found!")
                );
        salaryService.findSalaryByEmployeeCodeAndTransferredDate(teacherCode, transferredDate);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/")
    public ResponseEntity<SalaryDto> update(@RequestParam("teacherCode") String teacherCode,
                                            @RequestParam("transferredDate") LocalDate transferredDate,
                                            @RequestBody SalaryRequest salaryRequest) throws ResourceNotFoundException {

        Salary editedSalary = salaryService.findSalaryByEmployeeCodeAndTransferredDate(teacherCode, transferredDate)
                .orElseThrow(() -> new ResourceNotFoundException(teacherCode + " not found!"));

        Teacher teacherRequest = teacherService.findTeacherByEmployeeCode(salaryRequest.getTeacherCode())
                .orElseThrow(() -> new ResourceNotFoundException(teacherCode + " not found!"));

        editedSalary.setPayRate(salaryRequest.getPayRate());
        editedSalary.setTransferredDate(salaryRequest.getTransferredDate());
        editedSalary.setCoefficientMultiplier(salaryRequest.getCoefficientMultiplier());

        Salary updatedSalary = salaryService.addSalary(editedSalary);

        return ResponseEntity.ok(SalaryMapper.INSTANCE.toDto(updatedSalary));
    }

}
