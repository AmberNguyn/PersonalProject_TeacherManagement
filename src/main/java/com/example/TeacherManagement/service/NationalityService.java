package com.example.TeacherManagement.service;

import com.example.TeacherManagement.api.request.NationalityRequest;
import com.example.TeacherManagement.entity.Nationality;

import java.util.List;
import java.util.Optional;

public interface NationalityService {
    List<Nationality> getAll();

    Nationality create(Nationality nationality);
    Nationality create(NationalityRequest nationalityRequest);

    Nationality update(NationalityRequest nationalityRequest, Integer id);

    Optional<Nationality> findById(Integer id);

    Optional<Nationality> findByCountryCode(String countryCode);

    void deleteById(Integer id);

    void deleteByCountryCode(String countryCode);
}
