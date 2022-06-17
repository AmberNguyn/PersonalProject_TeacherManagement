package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Clazz;
import com.example.TeacherManagement.service.dto.ClazzHaveNotBeenAssignedDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
    Clazz findClassByClassId(String classId);

    // find classId that haven't been assigned


    @Query(nativeQuery = true)
    List<ClazzHaveNotBeenAssignedDto> findClassesThatHaveNotBeenAssigned();

}
