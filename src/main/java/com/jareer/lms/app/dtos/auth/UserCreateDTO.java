package com.jareer.lms.app.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record UserCreateDTO(
        @NotBlank(message = "Email must not be blank")
        @Email(regexp = "^(.+)@(.+)$", message = "Email is not valid")
        String email,
        @NotBlank(message = "Full Name must not be blank")
        String fullName,
        @Size(min = 3, max = 30, message = "length of password must be between 3 and 30")
        @NotBlank(message = "password must not be blank")
        String password
) implements Serializable {
}