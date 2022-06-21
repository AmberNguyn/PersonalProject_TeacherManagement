package com.example.TeacherManagement.service;

import com.example.TeacherManagement.api.request.ContractRequest;
import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.service.dto.TeachersAndExpiredContractsDto;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    List<Contract> getAll();

    Contract create(Contract contract);
    Contract create(ContractRequest contractRequest);

    Contract update(ContractRequest contractRequest, Integer id);

    List<Contract> findByEmployeeCode(String teacherCode);

    Optional<Contract> findByContractId(String contractId);

    Optional<Contract> findById(Integer id);

    void deleteByContractId(String contractId);

    void deleteById(Integer id);

    List<TeachersAndExpiredContractsDto> findTeachersAndTheirExpiredContractInYear(Integer year);
}
