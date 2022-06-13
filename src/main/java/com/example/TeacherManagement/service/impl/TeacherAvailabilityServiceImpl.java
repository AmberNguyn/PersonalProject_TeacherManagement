package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.TeacherAvailability;
import com.example.TeacherManagement.repository.TeacherAvailabilityRepository;
import com.example.TeacherManagement.service.TeacherAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherAvailabilityServiceImpl implements TeacherAvailabilityService {
    @Autowired
    private final TeacherAvailabilityRepository teacherAvailabilityRepository;

    @Override
    public List<TeacherAvailability> getAll() {
        return teacherAvailabilityRepository.findAll();
    }

    @Override
    public TeacherAvailability addTeacherAvailability(TeacherAvailability teacherAvailability) {
        return teacherAvailabilityRepository.save(teacherAvailability);
    }

    @Override
    public List<TeacherAvailability> findTeacherAvailabilityByEmployeeCode(String teacherCode) {
        return teacherAvailabilityRepository.findTeacherAvailabilityByTeacherEmployeeCodeContaining(teacherCode);
    }

    @Override
    public void deleteTeacherAvailabilityListByEmployeeCode(String teacherCode) {
        teacherAvailabilityRepository.deleteAll(teacherAvailabilityRepository.findTeacherAvailabilityByTeacherEmployeeCodeContaining(teacherCode));
    }
}
