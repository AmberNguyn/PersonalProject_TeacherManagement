package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.TeacherAvailability;
import com.example.TeacherManagement.entity.WorkingDay;

import java.util.List;

public interface TeacherAvailabilityService {
    List<TeacherAvailability> getAll();

    TeacherAvailability addTeacherAvailability(TeacherAvailability teacherAvailability);

    List<TeacherAvailability> findTeacherAvailabilityByEmployeeCode(String teacherCode);

    void deleteTeacherAvailabilityListByEmployeeCode(String teacherCode);

    TeacherAvailability findTeacherAvailabilityByEmployeeCodeAndWorkingDay(String teacherCode, WorkingDay workingDay);
}
