package com.example.TeacherManagement.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {

    @NotNull
    private Integer roomNumber;

    private Integer numberOfTable;

    private Double roomSize;

}
