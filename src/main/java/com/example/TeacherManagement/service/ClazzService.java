package com.example.TeacherManagement.service;

import com.example.TeacherManagement.api.request.ClazzRequest;
import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.service.dto.ClazzHaveNotBeenAssignedDto;

import java.util.List;
import java.util.Optional;

public interface ClazzService {
    List<Clazz> getAll();

    Clazz create(Clazz clazz);
    Clazz create (ClazzRequest clazzRequest);

    Clazz update(ClazzRequest clazzRequest, Integer id);

    Optional<Clazz> findByClassId(String classId);

    Optional<Clazz> findById(Integer id);

    void deleteByClassId(String classId);

    void deleteById(Integer id);

    List<ClazzHaveNotBeenAssignedDto> findClassesThatHaveNotBeenAssigned();
}
