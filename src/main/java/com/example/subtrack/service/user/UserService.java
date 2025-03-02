package com.example.subtrack.service.user;

import com.example.subtrack.dto.UserDto;

import java.util.UUID;

public interface UserService {
    UUID createUser(UserDto user);

    UserDto getUserById(UUID userId);

    UUID updateUser(UserDto user);

    void deleteUser(UUID userId);
}