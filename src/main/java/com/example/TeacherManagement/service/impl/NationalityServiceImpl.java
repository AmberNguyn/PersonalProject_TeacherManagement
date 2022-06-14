package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.Nationality;
import com.example.TeacherManagement.repository.NationalityRepository;
import com.example.TeacherManagement.service.NationalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NationalityServiceImpl implements NationalityService {
    @Autowired
    private final NationalityRepository nationalityRepository;

    @Override
    public List<Nationality> getAll() {
        return nationalityRepository.findAll();
    }

    @Override
    public Nationality addNationality(Nationality nationality) {
        return nationalityRepository.save(nationality);
    }

    @Override
    public Optional<Nationality> findNationalityById(Integer id) {
        return nationalityRepository.findById(id);
    }

    @Override
    public Optional<Nationality> findNationalityByCountryCode(String countryCode) {
        return Optional.of(nationalityRepository.findNationalityByCountryCode(countryCode));
    }

    @Override
    public void deleteNationalityById(Integer id) {
            nationalityRepository.deleteById(id);
    }
}
