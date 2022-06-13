package com.example.TeacherManagement.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusRequest {

    private String campusCode;
    private String name;
    private String address;
    private Integer numberOfRooms;

}
