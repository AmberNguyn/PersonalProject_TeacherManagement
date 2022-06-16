package com.example.TeacherManagement.repository;

import com.example.TeacherManagement.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
    Clazz findClassByClassId(String classId);

    // find classId that haven't been assigned

//    SELECT tc.employee_code, count(ad.contract_id)
//    FROM assignment_detail ad JOIN contract ct ON ad.contract_id = ct.id
//    JOIN teacher tc ON ct.teacher_id = tc.id
//    WHERE (EXTRACT (MONTH FROM ad.course_start_date) = 6 )





}
