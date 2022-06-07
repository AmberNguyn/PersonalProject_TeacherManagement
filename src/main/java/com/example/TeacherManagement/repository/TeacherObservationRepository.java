package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.TeacherObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherObservationRepository extends JpaRepository<TeacherObservation, Integer> {
}
