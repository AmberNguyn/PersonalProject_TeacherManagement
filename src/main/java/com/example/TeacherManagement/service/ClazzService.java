package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Clazz;

import java.util.List;
import java.util.Optional;

public interface ClazzService {
    List<Clazz> getAll();

    Clazz addClass(Clazz clazz);

    Optional<Clazz> findClassByClassId(String classId);

    void deleteClassByClassId(String classId);

}
