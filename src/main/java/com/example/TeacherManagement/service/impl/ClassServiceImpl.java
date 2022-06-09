package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.Class;
import com.example.TeacherManagement.repository.ClassRepository;
import com.example.TeacherManagement.service.ClassService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {
    @Autowired
    private final ClassRepository classRepository;

    @Override
    public List<Class> getAll() {
        return classRepository.findAll();
    }

    @Override
    public Class addClass(Class clazz) {
        return classRepository.save(clazz);
    }

    @Override
    public Optional<Class> findClassByClassId(String classId) {
        return Optional.of(classRepository.findClassByClassId(classId));
    }

    @Override
    public void deleteClassByClassId(String classId) {
        classRepository.delete(classRepository.findClassByClassId(classId));
    }
}
