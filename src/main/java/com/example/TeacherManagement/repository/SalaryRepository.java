package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Salary;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    //for deleting
    Salary findSalaryByTeacherEmployeeCodeAndTransferredDate(String teacherCode, LocalDate transferredDate);

    //for finding salaryList
    List<Salary> findSalaryByTeacherEmployeeCodeAndTransferredDateBetween(String teacherCode, LocalDate firstDate, LocalDate lastDate);
}
