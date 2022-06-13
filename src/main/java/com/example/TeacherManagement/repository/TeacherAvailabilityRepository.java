package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.TeacherAvailability;
import com.example.TeacherManagement.entity.WorkingDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherAvailabilityRepository extends JpaRepository<TeacherAvailability, Integer> {
    List<TeacherAvailability> findTeacherAvailabilityByTeacherEmployeeCodeContaining(String teacherCode);

    TeacherAvailability findTeacherAvailabilityByTeacherEmployeeCodeContainingAndWorkingDay(String teacherCode, WorkingDay workingDay);


}
