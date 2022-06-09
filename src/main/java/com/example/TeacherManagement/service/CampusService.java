package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Campus;

import java.util.List;
import java.util.Optional;

public interface CampusService {
    List<Campus> getAll();

    Campus addCampus(Campus campus);

    Optional<Campus> findCampusByName(String campusName);

    void deleteCampusByName(String campusName);
}
