package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
    Clazz findClassByClassId(String classId);

    // find classId that haven't been assigned

}
