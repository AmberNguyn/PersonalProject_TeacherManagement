package com.example.TeacherManagement.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NationalityRequest {
    private String countryCode;
    private String country;
    private String nationality;
}
