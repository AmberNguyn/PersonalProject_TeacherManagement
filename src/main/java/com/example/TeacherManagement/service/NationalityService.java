package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Nationality;

import java.util.List;
import java.util.Optional;

public interface NationalityService {
    List<Nationality> getAll();

    Nationality addNationality(Nationality nationality);

    Optional<Nationality> findNationalityById(Integer id);

    Optional<Nationality> findNationalityByCountryCode(String countryCode);

    void deleteNationalityById(Integer id);
}
