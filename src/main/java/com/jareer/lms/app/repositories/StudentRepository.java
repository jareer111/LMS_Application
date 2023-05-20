package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}