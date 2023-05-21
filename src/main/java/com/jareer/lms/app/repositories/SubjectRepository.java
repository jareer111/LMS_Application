package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    @Query("from Subject s where s.id = :subjectId and s.deleted = false")
    Optional<Subject> findBySubjectId(Integer subjectId);

    @Query(value = """
            select * from subject s where s.id in (
                select subjects_id from journal_subjects where journal_id in (
                    select journal_id from journal_students where students_id = :studentID
                )
            )  
            """, nativeQuery = true)
    Optional<List<Subject>> findSubjectsByStudentId(Integer studentID);
}