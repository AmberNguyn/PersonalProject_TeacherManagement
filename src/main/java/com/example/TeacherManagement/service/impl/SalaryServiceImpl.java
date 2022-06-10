package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.Salary;
import com.example.TeacherManagement.repository.SalaryRepository;
import com.example.TeacherManagement.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private final SalaryRepository salaryRepository;

    @Override
    public List<Salary> getAll() {
        return salaryRepository.findAll();
    }

    @Override
    public Salary addSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public List<Salary> findSalaryListByEmployeeCodeAndTransferredDateBetween(String teacherCode, LocalDate firstDate, LocalDate lastDate) {
        return salaryRepository.findSalaryByTeacherEmployeeCodeAndTransferredDateBetween(teacherCode, firstDate, lastDate);
    }

    @Override
    public Optional<Salary> findSalaryByEmployeeCodeAndTransferredDate(String teacherCode, LocalDate transferredDate) {
        return Optional.of(salaryRepository.findSalaryByTeacherEmployeeCodeAndTransferredDate(teacherCode, transferredDate));
    }


    @Override
    public void deleteSalaryByEmployeeCode(String teacherCode, LocalDate transferredDate) {
        salaryRepository.delete(salaryRepository.findSalaryByTeacherEmployeeCodeAndTransferredDate(teacherCode, transferredDate));
    }
}
