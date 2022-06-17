package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.repository.ClazzRepository;
import com.example.TeacherManagement.service.ClazzService;
import com.example.TeacherManagement.service.dto.ClazzHaveNotBeenAssignedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private final ClazzRepository clazzRepository;

    @Override
    public List<Clazz> getAll() {
        return clazzRepository.findAll();
    }

    @Override
    public Clazz addClass(Clazz clazz) {
        return clazzRepository.save(clazz);
    }

    @Override
    public Optional<Clazz> findClassByClassId(String classId) {
        return Optional.of(clazzRepository.findClassByClassId(classId));
    }

    @Override
    public Optional<Clazz> findClassById(Integer id) {
        return clazzRepository.findById(id);
    }

    @Override
    public void deleteClassByClassId(String classId) {
        clazzRepository.delete(clazzRepository.findClassByClassId(classId));
    }

    @Override
    public List<ClazzHaveNotBeenAssignedDto> findClassesThatHaveNotBeenAssigned() {
        return clazzRepository.findClassesThatHaveNotBeenAssigned();
    }
}
