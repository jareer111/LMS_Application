package com.jareer.lms;

import com.jareer.lms.app.domains.user.Role;
import com.jareer.lms.app.domains.user.User;
import com.jareer.lms.app.enums.UserStatus;
import com.jareer.lms.app.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.Set;

@EnableMethodSecurity
@SpringBootApplication
@RequiredArgsConstructor
public class LMSApplication {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(LMSApplication.class, args);
    }


//    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            User student = new User(1,
                    "student@gmail.com",
                    "$2a$10$cxdnqfK3MCxdA82YnuPoku1dDHptndFs.bk.GTElc2molq2MZKaZm",
                    null,
                    Role.STUDENT,
                    UserStatus.ACTIVE);
            User teacher = new User(2,
                    "teacher@gmail.com",
                    "$2a$10$cxdnqfK3MCxdA82YnuPoku1dDHptndFs.bk.GTElc2molq2MZKaZm",
                    null,
                    Role.TEACHER,
                    UserStatus.ACTIVE);
            User admin = new User(3,
                    "admin@gmail.com",
                    "$2a$10$cxdnqfK3MCxdA82YnuPoku1dDHptndFs.bk.GTElc2molq2MZKaZm",
                    null,
                    Role.ADMIN,
                    UserStatus.ACTIVE);
            userRepository.saveAll(Set.of(student, teacher, admin));
        };
    }
}
