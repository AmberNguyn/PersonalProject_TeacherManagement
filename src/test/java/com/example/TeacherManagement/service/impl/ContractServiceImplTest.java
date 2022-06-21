package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.service.ContractService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ContractServiceImplTest {
    @Autowired
    private ContractService contractService;

    @Test
    void testFindTeachersListWhoHaveCertificate_shouldReturnAListOf2Teachers_whenInputIsIELTS() {
        assertEquals(4, contractService.findTeachersAndTheirExpiredContractInYear(2022).size());
    }

}