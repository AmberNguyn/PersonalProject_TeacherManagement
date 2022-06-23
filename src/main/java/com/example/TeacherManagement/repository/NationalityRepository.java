package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
    Optional<Nationality> findNationalityByCountryCode(String countryCode);

}
