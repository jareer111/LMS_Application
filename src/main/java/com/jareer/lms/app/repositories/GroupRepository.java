package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("from groups g where g.id = :id and g.deleted = false")
    Optional<Group> findGroupById(Integer id);

    @Query(value = """
             select g.name,count(s.id)
             from groups g
             left join
             User s on g.id = s.group.id
             where g.faculty.id = :facultyId
             group by g.id
             order by count(s.id) desc
            """)
    Optional<List<Object[]>> findGroupsAndCountStudentsByFacultyId(Integer facultyId);

    @Query("from groups g where g.deleted = false")
    Page<Group> findAllGroup(PageRequest of);
}