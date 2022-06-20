package com.example.TeacherManagement.security.service;

import com.example.TeacherManagement.security.service.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getUsers();

}
