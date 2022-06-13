package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Integer> {
    Campus findCampusByCampusCodeIgnoreCase(String campusCode);

}
