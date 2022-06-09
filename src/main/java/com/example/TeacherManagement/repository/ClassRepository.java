package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    Class findClassByClassId(String classId);
}
