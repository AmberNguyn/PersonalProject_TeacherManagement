package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.repository.ContractRepository;
import com.example.TeacherManagement.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    @Autowired
    private final ContractRepository contractRepository;

    @Override
    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public List<Contract> findContractByEmployeeCode(String teacherCode) {
        return contractRepository.findContractByTeacherEmployeeCodeIgnoreCase(teacherCode);
    }

    @Override
    public Optional<Contract> findContractByContractId(String contractId) {
        return Optional.of(contractRepository.findContractByContractId(contractId));
    }

    @Override
    public void deleteContractByContractId(String contractId){
            contractRepository.delete(contractRepository.findContractByContractId(contractId));
    }





}
