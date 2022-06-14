package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.ContractRequest;
import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.ContractService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.ContractDto;
import com.example.TeacherManagement.service.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(ContractResource.PATH)
public class ContractResource {
    @Autowired
    private ContractService contractService;
    @Autowired
    private TeacherService teacherService;


    public static final String PATH = "/api/contracts";

    @GetMapping
    public ResponseEntity<List<ContractDto>> getAll() {
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDtos(contractService.getAll()));
    }

    @GetMapping("/find")
    public ResponseEntity<ContractDto> getContractBy(@RequestParam("teacherCode") String teacherCode) throws ResourceNotFoundException {
        Contract contract = contractService.findContractByEmployeeCode(teacherCode)
                .orElseThrow(() -> new ResourceNotFoundException(teacherCode + "'s contract not found!"));
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contract));
    }


    @PostMapping
    public ResponseEntity<ContractDto> create(@RequestBody ContractRequest contractRequest) throws ResourceNotFoundException {
        Teacher teacherRequest = teacherService.findTeacherByEmployeeCode(contractRequest.getTeacherCode())
                .orElseThrow(() -> new ResourceNotFoundException(contractRequest.getTeacherCode() + " not found!"));

        //create contract for old teachers only
        Contract createdContract = contractService.addContract(
                new Contract(
                        null,
                        contractRequest.getContractId(),
                        contractRequest.getStartDate(),
                        contractRequest.getEndDate(),
                        contractRequest.getPayRate(),
                        contractRequest.getBank(),
                        contractRequest.getAccountNumber(),
                        contractRequest.getBranch(),
                        contractRequest.getAccountName(),
                        contractRequest.getDescription(),
                        contractRequest.isSigned(),
                        teacherRequest
                )
        );

        return ResponseEntity.created(URI.create(ContractResource.PATH + "/" + createdContract.getId()))
                .body(ContractMapper.INSTANCE.toDto(contractService.addContract(createdContract)));
    }



    @DeleteMapping("/")
    public ResponseEntity<Void> delete(@RequestParam("teacherCode") String teacherCode) throws ResourceNotFoundException {
        Contract contract = contractService.findContractByEmployeeCode(teacherCode)
                .orElseThrow(() -> new ResourceNotFoundException(teacherCode + "'s contract not found!"));
        contractService.deleteContractByEmployeeCode(teacherCode);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<ContractDto> update(@RequestParam("teacherCode") String teacherCode,
                                              @RequestBody ContractRequest contractRequest) throws ResourceNotFoundException {
        Teacher teacherRequest = teacherService.findTeacherByEmployeeCode(contractRequest.getTeacherCode())
                .orElseThrow(() -> new ResourceNotFoundException(contractRequest.getTeacherCode() + " not found!"));

        Contract editedContract = contractService.findContractByEmployeeCode(teacherCode)
                .orElseThrow(() -> new ResourceNotFoundException(teacherCode + " not found!"));

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

        Contract updatedContract = contractService.addContract(editedContract);
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(updatedContract));

    }

}
