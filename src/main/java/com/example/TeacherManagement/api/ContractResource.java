package com.example.TeacherManagement.api;

import com.example.TeacherManagement.service.ContractService;
import com.example.TeacherManagement.service.dto.ContractDto;
import com.example.TeacherManagement.service.mapper.ContractMapper;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(ContractResource.PATH)
public class ContractResource {
    @Autowired
    private ContractService contractService;

    public static final String PATH = "/api/contract";

    @GetMapping
    public ResponseEntity<List<ContractDto>> getAll()
    {
        return ResponseEntity.ok(ContractMapper.INSTANCE.toDtos(contractService.getAll()));
    }
}
