package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Integer> {
}