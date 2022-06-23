package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.api.request.NationalityRequest;
import com.example.TeacherManagement.entity.Nationality;
import com.example.TeacherManagement.exception.BusinessLogicException;
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
    public Nationality create(Nationality nationality) {
        return nationalityRepository.save(nationality);
    }

    @Override
    public Nationality create(NationalityRequest nationalityRequest) {

        Nationality createdNationality = new Nationality();
        createdNationality.setCountryCode(nationalityRequest.getCountryCode());
        createdNationality.setCountry(nationalityRequest.getCountry());
        createdNationality.setNationality(nationalityRequest.getNationality());

        return nationalityRepository.save(createdNationality);
    }

    @Override
    public Nationality update(NationalityRequest nationalityRequest, Integer id) {
        Nationality editedNationality = nationalityRepository.findById(id)
                .orElseThrow(BusinessLogicException::NationalityIdNotFound);

        editedNationality.setCountryCode(nationalityRequest.getCountryCode());
        editedNationality.setCountry(nationalityRequest.getCountry());
        editedNationality.setNationality(nationalityRequest.getNationality());

        return nationalityRepository.save(editedNationality);
    }

    @Override
    public Optional<Nationality> findById(Integer id) {
        return nationalityRepository.findById(id);
    }

    @Override
    public Optional<Nationality> findByCountryCode(String countryCode) {
        return nationalityRepository.findNationalityByCountryCode(countryCode);
    }

    @Override
    public void deleteById(Integer id) {
        nationalityRepository.deleteById(id);
    }

    @Override
    public void deleteByCountryCode(String countryCode) {
        if (nationalityRepository.findNationalityByCountryCode(countryCode).isPresent()) {
            nationalityRepository.delete(nationalityRepository.findNationalityByCountryCode(countryCode).get());
        }
    }
}
