package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.TeacherAvailability;

import java.util.List;
import java.util.Optional;

public interface TeacherAvailabilityService {
    List<TeacherAvailability> getAll();

    TeacherAvailability addTeacherAvailability(TeacherAvailability teacherAvailability);

    Optional<TeacherAvailability> findTeacherAvailabilityByEmployeeCode(String teacherCode);

    void deleteTeacherAvailabilityByEmployeeCode(String teacherCode);
}
