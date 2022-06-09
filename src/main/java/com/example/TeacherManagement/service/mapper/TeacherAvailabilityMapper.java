package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.TeacherAvailability;
import com.example.TeacherManagement.service.dto.TeacherAvailabilityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeacherAvailabilityMapper {
    TeacherAvailabilityMapper INSTANCE = Mappers.getMapper(TeacherAvailabilityMapper.class);

    @Mapping(source = "teacher.employeeCode", target = "teacherCode")
    @Mapping(source = "teacher.firstName", target = "firstName")
    @Mapping(source = "teacher.lastName", target = "lastName")
    @Mapping(source = "teacher.phoneNumber", target = "phoneNumber")

    TeacherAvailabilityDto toDto(TeacherAvailability teacherAvailability);

    List<TeacherAvailabilityDto> toDtos(List<TeacherAvailability> teacherAvailabilities);
}
