package com.example.subtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for {@link UserDto}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}