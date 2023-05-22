package com.jareer.lms.app.dtos;

import com.jareer.lms.app.domains.user.Role;
import com.jareer.lms.app.enums.UserStatus;

public record StudentDetailsDTO(
        String fullName,
        Role role,
        UserStatus status,
        String username,
        String groupName,
        String facultyName) {
}
