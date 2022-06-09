package com.example.TeacherManagement.service;

import com.example.TeacherManagement.entity.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    List<Contract> getAll();

    Contract addContract(Contract contract);

    Optional<Contract> findContractByEmployeeCode(String teacherCode);

    void deleteContractByEmployeeCode(String teacherCode);
}
