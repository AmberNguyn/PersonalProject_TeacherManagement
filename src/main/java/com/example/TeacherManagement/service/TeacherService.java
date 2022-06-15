package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.entity.TeacherType;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> getAll();

    Teacher addTeacher(Teacher teacher);

    Optional<Teacher> findTeacherByEmployeeCode(String teacherCode);

    void deleteTeacherByEmployeeCode(String teacherCode);


    //find a list of teachers based on teacher type: vietnamese or expatriate
    List<Teacher> findTeacherByTeacherType(TeacherType teacherType);
}
