package com.jareer.lms.app.dtos.auth;

public record NewPasswordDTO(
        String code,
        String password,
        String confirmPassword) {
}
