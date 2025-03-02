package com.example.subtrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link UserDto}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @Size(min = 2, max = 250)
    private String firstName;
    @Size(min = 2, max = 250)
    private String lastName;
    @Size(min = 6, max = 254)
    @Email
    private String email;
    private String password;
}