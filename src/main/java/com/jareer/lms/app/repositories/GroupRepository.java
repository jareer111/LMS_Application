package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}