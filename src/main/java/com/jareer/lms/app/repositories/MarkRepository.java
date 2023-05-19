package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
}