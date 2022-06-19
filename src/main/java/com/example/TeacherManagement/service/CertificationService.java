package com.example.TeacherManagement.service;

import com.example.TeacherManagement.api.request.CertificationRequest;
import com.example.TeacherManagement.entity.Certification;

import java.util.List;
import java.util.Optional;

public interface CertificationService {
    List<Certification> getAll();

    Certification create(Certification certification);
    Certification create(CertificationRequest certificationRequest);

    Optional<Certification> findById(Integer id);

    Certification update(CertificationRequest certificationRequest, Integer id);

    void deleteById(Integer id);



}
