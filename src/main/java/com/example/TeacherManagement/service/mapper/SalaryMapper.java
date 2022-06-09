package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Salary;
import com.example.TeacherManagement.service.dto.SalaryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SalaryMapper {
    SalaryMapper INSTANCE = Mappers.getMapper(SalaryMapper.class);

    @Mapping(source = "teacher.firstName", target = "teacherFirstName")
    @Mapping(source = "teacher.lastName", target = "teacherLastName")
    @Mapping(source = "teacher.dateOfBirth", target = "dateOfBirth")

    SalaryDto toDto(Salary salary);

    List<SalaryDto> toDtos(List<Salary> salaries);
}
