package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
