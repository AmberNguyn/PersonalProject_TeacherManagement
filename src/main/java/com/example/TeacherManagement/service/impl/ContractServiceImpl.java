package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.api.request.ContractRequest;
import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.repository.ContractRepository;
import com.example.TeacherManagement.service.ContractService;
import com.example.TeacherManagement.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    @Autowired
    private final ContractRepository contractRepository;
    @Autowired
    private TeacherService teacherService;

    @Override
    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract create(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract create(ContractRequest contractRequest) {
        Teacher teacherRequest = teacherService.findByEmployeeCode(contractRequest.getTeacherCode())
                .orElseThrow(BusinessLogicException::TeacherCodeNotFound);
        log.info("Searched teacher code: "+ contractRequest.getTeacherCode());

        Contract createdContract = new Contract();
        createdContract.setContractId(contractRequest.getContractId());
        createdContract.setStartDate(contractRequest.getStartDate());
        createdContract.setEndDate(contractRequest.getEndDate());
        createdContract.setPayRate(contractRequest.getPayRate());
        createdContract.setBank(contractRequest.getBank());
        createdContract.setAccountNumber(contractRequest.getAccountNumber());
        createdContract.setAccountName(contractRequest.getAccountName());
        createdContract.setBranch(contractRequest.getBranch());
        createdContract.setDescription(contractRequest.getDescription());
        createdContract.setSigned(contractRequest.isSigned());
        createdContract.setTeacher(teacherRequest);

        return contractRepository.save(createdContract);
    }

    @Override
    public Contract update(ContractRequest contractRequest, Integer id){
        Teacher teacherRequest = teacherService.findByEmployeeCode(contractRequest.getTeacherCode())
                .orElseThrow(BusinessLogicException::TeacherCodeNotFound);
        log.info("Searched teacher code: {}", contractRequest.getTeacherCode());

        Contract editedContract = contractRepository.findById(id)
                .orElseThrow(BusinessLogicException::ContractIdNotFound);
        log.info("Searched contract id: {}", id);

        if (!editedContract.isSigned()) {
            editedContract.setContractId(contractRequest.getContractId());
            editedContract.setStartDate(contractRequest.getStartDate());
            editedContract.setEndDate(contractRequest.getEndDate());
            editedContract.setPayRate(contractRequest.getPayRate());
            editedContract.setBank(contractRequest.getBank());
            editedContract.setAccountNumber(contractRequest.getAccountNumber());
            editedContract.setBranch(contractRequest.getBranch());
            editedContract.setAccountName(contractRequest.getAccountName());
            editedContract.setDescription(contractRequest.getDescription());
            editedContract.setSigned(contractRequest.isSigned());
            editedContract.setTeacher(teacherRequest);
        }
        return contractRepository.save(editedContract);
    }

    @Override
    public List<Contract> findByEmployeeCode(String teacherCode) {
        return contractRepository.findContractByTeacherEmployeeCodeIgnoreCase(teacherCode);
    }

    @Override
    public Optional<Contract> findByContractId(String contractId) {
        return Optional.of(contractRepository.findContractByContractId(contractId));
    }

    @Override
    public Optional<Contract> findById(Integer id) {
        return contractRepository.findById(id);
    }

    @Override
    public void deleteByContractId(String contractId) {
        contractRepository.delete(contractRepository.findContractByContractId(contractId));
    }

    @Override
    public void deleteById(Integer id) {
        contractRepository.deleteById(id);
    }


}
