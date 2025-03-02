package com.example.subtrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class NewUserRequest {
    @NotNull
    @Size(min = 2, max = 250)
    @NotBlank
    private String firstName;
    @NotNull
    @Size(min = 2, max = 250)
    @NotBlank
    private String lastName;
    @NotNull
    @Size(min = 6, max = 254)
    @Email
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;
}