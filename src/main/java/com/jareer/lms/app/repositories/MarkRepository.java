package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Mark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MarkRepository extends JpaRepository<Mark, Integer> {

    @Query("from Mark m where m.id=:id and  m.deleted=false")
    Optional<Mark> findMarkById(Integer id);

    @Query("from Mark  m where m.deleted=false")
    Page<Mark> findAllMark(PageRequest of);
}