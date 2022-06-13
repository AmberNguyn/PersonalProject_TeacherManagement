package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.ContractRequest;
import com.example.TeacherManagement.entity.Campus;
import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.exception.ResourceNotFoundException;
import com.example.TeacherManagement.service.CampusService;
import com.example.TeacherManagement.service.ContractService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.ContractDto;
import com.example.TeacherManagement.service.mapper.ContractMapper;
import org.apache.coyote.Response;
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
    @Autowired
    private CampusService campusService;

    public static final String PATH = "/api/contract";

    @GetMapping
    public ResponseEntity<List<ContractDto>> getAll() {
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDtos(contractService.getAll()));
    }

    @GetMapping("/{teacherCode}")
    public ResponseEntity<ContractDto> getContractBy(@PathVariable String teacherCode) throws ResourceNotFoundException {
        Contract contract = contractService.findContractByEmployeeCode(teacherCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s contract not found!")
                );
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contract));
    }



    @PostMapping
    public ResponseEntity<ContractDto> create(@RequestBody ContractRequest contractRequest) throws ResourceNotFoundException {
        Teacher teacherRequest = teacherService.findTeacherByEmployeeCode(contractRequest.getTeacher_code())
                .orElseThrow(() -> new ResourceNotFoundException(contractRequest.getTeacher_code() + " not found!"));

        Campus campusRequest = campusService.findCampusByCampusCode(contractRequest.getCampus_code())
                .orElseThrow(() -> new ResourceNotFoundException(contractRequest.getCampus_code() + " not found!"));

        //create contract for old teachers only
        Contract createdContract = contractService.addContract(
                new Contract(
                        null,
                        contractRequest.getContractId(),
                        contractRequest.getContractType(),
                        contractRequest.getStartDate(),
                        contractRequest.getEndDate(),
                        teacherRequest,
                        campusRequest
                )
        );

        return ResponseEntity.created(URI.create(ContractResource.PATH + "/" + createdContract.getId()))
                .body(ContractMapper.INSTANCE.toDto(contractService.addContract(createdContract)));
    }

    @DeleteMapping("/{teacherCode}")
    public ResponseEntity<Void> delete(@PathVariable("teacherCode") String teacherCode) throws ResourceNotFoundException {
        Contract contract = contractService.findContractByEmployeeCode(teacherCode)
                .orElseThrow(
                        () -> new ResourceNotFoundException(teacherCode + "'s contract not found!")
                );
        contractService.deleteContractByEmployeeCode(teacherCode);
        return ResponseEntity.noContent().build();
    }

}
