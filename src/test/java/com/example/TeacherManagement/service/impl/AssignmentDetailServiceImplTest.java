package com.example.TeacherManagement.service.impl;

import com.example.TeacherManagement.service.AssignmentDetailService;
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
class AssignmentDetailServiceImplTest {
    @Autowired
    private AssignmentDetailService assignmentDetailService;

    //list of teachers who have leave not and not meet required hours
    @Test
    void findTeacherListWhoHaveLeaveNotAndNotMeetRequireHours_shouldReturnAListOf4Teachers()
    {
        assertEquals(4, assignmentDetailService.findTeacherListsWhoHaveLeaveNoteAndNoMeetRequiredHours().size());
    }

    @Test
    void findTeacherListAndTheirNumberOfClassWithinAMonth_shouldReturnAListOf6Teachers_whenFound()
    {
        assertEquals(6, assignmentDetailService.findTeacherAndTheirNumberOfClassInAMonth(6).size());
    }

    @Test
    void findTeachersAndTotalActiveHours_shouldReturnAListOf6Teachers_whenFound()
    {
        assertEquals(6, assignmentDetailService.findTeachersAndTheirTotalActiveHoursInAMonth(6).size());
    }
}