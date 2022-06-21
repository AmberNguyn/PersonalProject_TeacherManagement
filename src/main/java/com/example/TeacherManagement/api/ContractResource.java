package com.example.TeacherManagement.api;

import com.example.TeacherManagement.api.request.ContractRequest;
import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.exception.BusinessLogicException;
import com.example.TeacherManagement.service.ContractService;
import com.example.TeacherManagement.service.TeacherService;
import com.example.TeacherManagement.service.dto.ContractDto;
import com.example.TeacherManagement.service.dto.TeachersAndExpiredContractsDto;
import com.example.TeacherManagement.service.mapper.ContractMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
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

    @GetMapping("/{id}")
    public ResponseEntity<ContractDto> getById(@PathVariable("id") Integer id){
        log.info("Searching contract id: {}", id);
        Contract contract = contractService.findById(id)
                .orElseThrow(BusinessLogicException::ContractIdNotFound);
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contract));
    }

    @GetMapping("/get-by-contract-id")
    public ResponseEntity<ContractDto> getByContractId(@RequestParam("contractId") String contractCode){
        log.info("Searching contract code: {}", contractCode);
        Contract contract = contractService.findByContractId(contractCode)
                .orElseThrow(() -> BusinessLogicException.notFound("ContractCodeNotFound", "Contract Code Not Found"));
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(contract));
    }

    @GetMapping("/get-by-teacher-code")
    public ResponseEntity<List<ContractDto>> getContractByTeacherCode(@RequestParam("teacherCode") String teacherCode) {
        List<Contract> contract = contractService.findByEmployeeCode(teacherCode);
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDtos(contract));
    }


    @PostMapping
    public ResponseEntity<ContractDto> create(@RequestBody ContractRequest contractRequest) {
        Contract createdContract = contractService.create(contractRequest);

        return ResponseEntity.created(URI.create(ContractResource.PATH + "/" + createdContract.getId()))
                .body(ContractMapper.INSTANCE.toDto(contractService.create(createdContract)));
    }



    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam(value = "contractId") String contractId ) {
        log.info("Searching contract id: {}", contractId);
        Contract contract = contractService.findByContractId(contractId)
                .orElseThrow(BusinessLogicException::ContractIdNotFound);
        contractService.deleteByContractId(contractId);
        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id){
        log.info("Searching id: {}", id);
        Contract contract = contractService.findById(id)
                .orElseThrow(BusinessLogicException::ContractIdNotFound);
        contractService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody ContractRequest contractRequest) {

            Contract updatedContract = contractService.update(contractRequest, id);
            return ResponseEntity.ok(ContractMapper.INSTANCE.toDto(updatedContract));
    }

    @GetMapping("/expire-in-year/{year}")
    public ResponseEntity<List<TeachersAndExpiredContractsDto>> findTeachersAndTheirExpiredContractsInYear(@PathVariable("year") Integer year){
        return ResponseEntity.ok(contractService.findTeachersAndTheirExpiredContractInYear(year));
    }

}
