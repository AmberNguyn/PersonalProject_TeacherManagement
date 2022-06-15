package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.entity.TeacherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findTeacherByEmployeeCode(String teacherCode);

    //find a list of teachers based on teacher type: vietnamese or expatriate
    List<Teacher> findTeacherByTeacherType(TeacherType teacherType);
}
