package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JournalRepository extends JpaRepository<Journal, Integer> {

    @Query("from Journal j where j.id = :journalId and j.deleted = false")
    Optional<Journal> findJournalById(Integer journalId);
    @Query("from Journal j where  j.deleted = false")
    Page<Journal> findAllJournal(PageRequest of);
}