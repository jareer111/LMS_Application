package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}