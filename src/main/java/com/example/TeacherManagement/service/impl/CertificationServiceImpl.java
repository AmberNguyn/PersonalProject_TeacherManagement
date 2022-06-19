package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.api.request.CertificationRequest;
import com.example.TeacherManagement.entity.Certification;
import com.example.TeacherManagement.exception.MyException;
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
    public Certification create(Certification certification) {
        return certificationRepository.save(certification);
    }

    @Override
    public Certification create(CertificationRequest certificationRequest) {
        Certification createdCertification = new Certification();

        createdCertification.setName(certificationRequest.getName());

        return certificationRepository.save(createdCertification);
    }

    @Override
    public Optional<Certification> findById(Integer id) {
        return certificationRepository.findById(id);
    }

    @Override
    public Certification update(CertificationRequest certificationRequest, Integer id){
        Certification editedCertification = certificationRepository.findById(id)
                .orElseThrow(MyException::CertificateIdNotFound);

        editedCertification.setName(certificationRequest.getName());
        return certificationRepository.save(editedCertification);
    }

    @Override
    public void deleteById(Integer id) {
        certificationRepository.deleteById(id);
    }
}
