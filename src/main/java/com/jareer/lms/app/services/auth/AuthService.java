package com.jareer.lms.app.services.auth;

import com.jareer.lms.app.configurations.jwt.JwtUtils;
import com.jareer.lms.app.domains.user.Role;
import com.jareer.lms.app.domains.user.User;
import com.jareer.lms.app.dtos.auth.RefreshTokenRequest;
import com.jareer.lms.app.dtos.auth.TokenRequest;
import com.jareer.lms.app.dtos.auth.TokenResponse;
import com.jareer.lms.app.dtos.auth.UserCreateDTO;
import com.jareer.lms.app.enums.TokenType;
import com.jareer.lms.app.mappers.UserMapper;
import com.jareer.lms.app.repositories.user.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public TokenResponse generateToken(@NonNull TokenRequest tokenRequest) {
        String email = tokenRequest.email();
        String password = tokenRequest.password();
        this.userRepository.findUserByUsername(email).orElseThrow(
                () -> new RuntimeException("User not found"));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        this.authenticationManager.authenticate(authentication);
        return this.jwtUtils.generateToken(email);
    }

    public User create(@NotNull UserCreateDTO dto) {

        userRepository.checkUniqueFields(dto.email()).ifPresent(user -> {
            throw new RuntimeException("%s email already exists ".formatted(dto.email()));
        });

        User user = UserMapper.INSTANCE.toEntity(dto);
        user.setPassword(this.passwordEncoder.encode(dto.password()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public TokenResponse refreshToken(@NotNull RefreshTokenRequest refreshTokenRequest) {

        String refreshToken = refreshTokenRequest.refreshToken();
        if (!this.jwtUtils.isTokenValid(refreshToken, TokenType.REFRESH))
            throw new CredentialsExpiredException("Token is invalid");

        String email = this.jwtUtils.getUsername(refreshToken, TokenType.REFRESH);
        this.userRepository.findByEmail(email);
        TokenResponse tokenResponse = TokenResponse.builder().refreshToken(refreshToken).refreshTokenExpiry(this.jwtUtils.getExpiry(refreshToken, TokenType.REFRESH)).build();
        return this.jwtUtils.generateAccessToken(email, tokenResponse);
    }


    public String addRole(String role, Integer userId) {

        return userRepository.findById(userId).map(user -> {
            user.setRole(Role.valueOf(role));
            userRepository.save(user);
            return "Role added";
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
