package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Contract;
import com.example.TeacherManagement.service.dto.ContractDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContractMapper {
    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

    @Mapping(source = "teacher.firstName", target = "firstName")
    @Mapping(source = "teacher.lastName", target = "lastName")
    @Mapping(source = "teacher.gender", target = "gender")
    @Mapping(source = "teacher.nationality", target = "nationality")
    @Mapping(source = "teacher.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "teacher.address", target = "address")
    @Mapping(source = "teacher.teacherType", target = "teacherType")
    @Mapping(source = "teacher.phoneNumber", target = "phoneNumber")

    @Mapping(source = "campus.name", target = "campusName")
    @Mapping(source = "campus.address", target = "campusAddress")


    ContractDto toDto(Contract contract);

    List<ContractDto> toDtos(List<Contract> contracts);
}
