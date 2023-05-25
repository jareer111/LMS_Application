package com.jareer.lms;

import com.jareer.lms.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;


@EnableMethodSecurity
@SpringBootApplication
@RequiredArgsConstructor
public class LMSApplication {
    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(LMSApplication.class, args);
    }


    // if you need ready users
   /* @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            User student = new User(1,
                    "student@gmail.com",
                    "1 student",
                    "$2a$10$cxdnqfK3MCxdA82YnuPoku1dDHptndFs.bk.GTElc2molq2MZKaZm",
                    null,
                    Role.STUDENT,
                    UserStatus.ACTIVE);
            User teacher = new User(2,
                    "teacher@gmail.com",
                    "2 teacher",
                    "$2a$10$cxdnqfK3MCxdA82YnuPoku1dDHptndFs.bk.GTElc2molq2MZKaZm",
                    null,
                    Role.TEACHER,
                    UserStatus.ACTIVE);
            User admin = new User(3,
                    "admin@gmail.com",
                    "3 admin",
                    "$2a$10$cxdnqfK3MCxdA82YnuPoku1dDHptndFs.bk.GTElc2molq2MZKaZm",
                    null,
                    Role.ADMIN,
                    UserStatus.ACTIVE);
            userRepository.saveAll(Set.of(student, teacher, admin));
        };
    }*/
}
