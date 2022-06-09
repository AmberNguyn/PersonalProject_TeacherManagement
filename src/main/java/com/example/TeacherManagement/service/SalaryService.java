package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Salary;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SalaryService {
    List<Salary> getAll();

    Salary addSalary(Salary salary);

    List<Salary> findSalaryListByEmployeeCodeAndTransferredDateBetween(String teacherCode, LocalDate firstDate, LocalDate lastDate);

    void deleteSalaryByEmployeeCode(String teacherCode, LocalDate transferredDate);
}
