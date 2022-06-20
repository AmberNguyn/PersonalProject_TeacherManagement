package com.example.TeacherManagement.security.service.impl;


import com.example.TeacherManagement.security.repository.UserRepository;
import com.example.TeacherManagement.security.service.UserService;
import com.example.TeacherManagement.security.service.dto.UserDTO;
import com.example.TeacherManagement.security.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<UserDTO> getUsers() {
                return UserMapper.INSTANCE.mapToDtos(userRepository.findAll());

    }
}
