package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    @Query("from Faculty f where f.id=:facultyID and f.deleted=false ")
    Optional<Faculty> findFacultyById(Integer facultyID);

    @Query("from Faculty f where f.deleted=false")
    Page<Faculty> findAllFaculty(PageRequest of);
}
