package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Class;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<Class> getAll();

    Class addClass(Class clazz);

    Optional<Class> findClassByClassId(String classId);

    void deleteClassByClassId(String classId);

}
