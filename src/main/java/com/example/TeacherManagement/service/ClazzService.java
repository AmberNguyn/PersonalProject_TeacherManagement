package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.service.dto.ClazzHaveNotBeenAssignedDto;

import java.util.List;
import java.util.Optional;

public interface ClazzService {
    List<Clazz> getAll();

    Clazz addClass(Clazz clazz);

    Optional<Clazz> findClassByClassId(String classId);

    Optional<Clazz> findClassById(Integer id);

    void deleteClassByClassId(String classId);

    List<ClazzHaveNotBeenAssignedDto> findClassesThatHaveNotBeenAssigned();
}
