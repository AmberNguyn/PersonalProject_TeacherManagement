package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Integer> {
    Nationality findNationalityByCountryCode(String countryCode);

}
