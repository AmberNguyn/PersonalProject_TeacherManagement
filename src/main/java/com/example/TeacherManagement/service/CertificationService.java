package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Certification;

import java.util.List;
import java.util.Optional;

public interface CertificationService {
    List<Certification> getAll();

    Certification addCertification(Certification certification);

    Optional<Certification> findCertificationById(Integer id);

    void deleteCertificationById(Integer id);



}
