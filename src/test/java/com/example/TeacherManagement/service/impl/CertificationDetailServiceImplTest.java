package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.service.CertificationDetailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CertificationDetailServiceImplTest {
    @Autowired
    private CertificationDetailService certificationDetailService;


    @Test
    void testFindTeachersListWhoHaveCertificate_shouldReturnAListOf2Teachers_whenInputIsIELTS() {
        assertEquals(2, certificationDetailService.findTeachersListWhoHaveCertificate("IELTS").size());
    }

    @Test
    void findTeachersWhoCanTeacherIELTS_shouldReturnAListOf2Teachers_whenFound(){
        assertEquals(2, certificationDetailService.findIELTSTeacherListByCertificationNameAndScoreGreaterThan("IELTS", 7.5).size());
    }


}