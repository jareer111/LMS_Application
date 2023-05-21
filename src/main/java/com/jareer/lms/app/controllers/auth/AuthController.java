package com.jareer.lms.app.controllers.auth;

import com.jareer.lms.app.domains.user.User;
import com.jareer.lms.app.dtos.auth.RefreshTokenRequest;
import com.jareer.lms.app.dtos.auth.TokenRequest;
import com.jareer.lms.app.dtos.auth.TokenResponse;
import com.jareer.lms.app.dtos.auth.UserCreateDTO;
import com.jareer.lms.app.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/api/v1/auth"})
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping({"/access/token"})
    public ResponseEntity<TokenResponse> generateToken(@Valid @RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(this.authService.generateToken(tokenRequest));
    }


    @PostMapping({"/refresh/token"})
    public ResponseEntity<TokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(this.authService.refreshToken(refreshTokenRequest));
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserCreateDTO dto) {
        return ResponseEntity.ok(this.authService.create(dto));
    }


    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping({"/addRole"})
    public ResponseEntity<String> addRole(@NonNull String role, @NonNull Integer userId) {
        return ResponseEntity.ok(this.authService.addRole(role, userId));
    }
}
