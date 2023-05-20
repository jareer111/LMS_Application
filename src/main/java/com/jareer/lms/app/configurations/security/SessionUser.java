package com.jareer.lms.app.configurations.security;

import com.jareer.lms.app.domains.user.User;
import com.jareer.lms.app.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SessionUser {
    private final UserRepository userRepository;

    public User user() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String name = authentication.getName();
        if (authentication.isAuthenticated()) {
            User byEmail = userRepository.findByEmail(name);
            System.out.println(byEmail);
            return byEmail;

        }
        return null;
    }

    public Integer id() {
        User user = user();
        if (Objects.isNull(user))
            return -1;
        return user.getId();
    }
}
