package com.example.TeacherManagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NationalityDto {
    private String countryCode;
    private String country;
    private String nationality;

}
