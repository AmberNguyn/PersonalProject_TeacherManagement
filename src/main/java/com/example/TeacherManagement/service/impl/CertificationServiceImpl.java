package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.Certification;
import com.example.TeacherManagement.repository.CertificationRepository;
import com.example.TeacherManagement.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {
    @Autowired
    private final CertificationRepository certificationRepository;

    @Override
    public List<Certification> getAll() {
        return certificationRepository.findAll();
    }

    @Override
    public Certification addCertification(Certification certification) {
        return certificationRepository.save(certification);
    }

    @Override
    public Optional<Certification> findCertificationById(Integer id) {
        return certificationRepository.findById(id);
    }

    @Override
    public void deleteCertificationById(Integer id) {
        certificationRepository.deleteById(id);
    }
}
