package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.entity.TeacherType;
import com.example.TeacherManagement.service.TeacherService;
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
class TeacherServiceImplTest {
    @Autowired
    private TeacherService teacherService;

    @Test
    void fsd()
    {
        assertEquals(2, teacherService.findTeacherByTeacherType(TeacherType.VIETNAMESE));
    }

}