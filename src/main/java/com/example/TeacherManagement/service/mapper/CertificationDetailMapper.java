package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Certification;
import com.example.TeacherManagement.entity.CertificationDetail;
import com.example.TeacherManagement.entity.Teacher;
import com.example.TeacherManagement.service.dto.CertificationDetailDto;
import com.example.TeacherManagement.service.dto.CertificationDto;
import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CertificationDetailMapper {
    CertificationDetailMapper INSTANCE = Mappers.getMapper(CertificationDetailMapper.class);

    //from Certification
    @Mapping(source = "certification.name", target = "name")

    //from teacher
    @Mapping(source = "teacher.employeeCode", target = "teacherCode")
    @Mapping(target = "fullName", expression = "java(certificationDetail.getTeacher().getFirstName() + \" \" + certificationDetail.getTeacher().getMiddleName() + \" \" + certificationDetail.getTeacher().getLastName())")

    CertificationDetailDto toDto(CertificationDetail certificationDetail);
    List<CertificationDetailDto> toDtos(List<CertificationDetail> certificationDetails);

    @AfterMapping
    default void setFullName(Teacher teacher, @MappingTarget CertificationDetailDto target)
    {
        target.setFullName(teacher.getFirstName() + " "  + teacher.getMiddleName() + " " + teacher.getFirstName());
    }
}
