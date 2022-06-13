package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.Campus;
import com.example.TeacherManagement.repository.CampusRepository;
import com.example.TeacherManagement.service.CampusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampusServiceImpl implements CampusService {
    @Autowired
    private final CampusRepository campusRepository;

    @Override
    public List<Campus> getAll() {
        return campusRepository.findAll();
    }

    @Override
    public Campus addCampus(Campus campus) {
        return campusRepository.save(campus);
    }

    @Override
    public Optional<Campus> findCampusByCampusCode(String campusCode) {
        return Optional.of(campusRepository.findCampusByCampusCodeIgnoreCase(campusCode));
    }

    @Override
    public void deleteCampusByName(String campusName) {
        campusRepository.delete(campusRepository.findCampusByCampusCodeIgnoreCase(campusName));
    }
}
