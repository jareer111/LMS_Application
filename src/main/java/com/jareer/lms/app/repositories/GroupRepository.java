package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    @Query("select g from groups g where g.id = :id and g.deleted = false")
    Optional<Group> findGroupById(Integer id);

    //    @Query("select g from groups g where g.faculty.id = :facultyId and g.deleted = false")
// TODO: 21/05/2023  continue query
    Optional<Group> findGroupsAndCountStudentsByFacultyId(Integer facultyId);
}