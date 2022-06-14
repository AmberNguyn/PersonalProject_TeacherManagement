package com.example.TeacherManagement.service.mapper;

import com.example.TeacherManagement.entity.Payment;
import com.example.TeacherManagement.service.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source = "assignmentDetail.contract.teacher.employeeCode", target = "employeeCode")
    @Mapping(source = "assignmentDetail.contract.teacher.firstName", target = "firstName")
    @Mapping(source = "assignmentDetail.contract.teacher.lastName", target = "lastName")

    @Mapping(source = "assignmentDetail.contract.bankAccount", target = "bankAccount")
    @Mapping(source = "assignmentDetail.contract.bank", target = "bank")
    @Mapping(source = "assignmentDetail.contract.accountName", target = "accountName")

    @Mapping(source = "assignmentDetail.activeHours", target = "activeHours")

    PaymentDto toDto(Payment payment);
    List<PaymentDto> toDtos(List<Payment> payments);
}
