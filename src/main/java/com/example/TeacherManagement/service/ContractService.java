package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    List<Contract> getAll();

    Contract addContract(Contract contract);

    List<Contract> findContractByEmployeeCode(String teacherCode);

    Optional<Contract> findContractByContractId(String contractId);

    void deleteContractByContractId(String contractId);


}
