package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
    Optional<Void> deleteUniversityById(Integer id);

    @Query("select u from University u where u.id = :id and u.deleted = false")
    Optional<University> findUniversityById(Integer id);

    @Query("select u from University u where u.deleted = false")
    Page<University> findAllUniversity(PageRequest of);
}
