package com.jareer.lms.app.repositories;

import com.jareer.lms.app.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where upper(u.username) = upper(?1)")
    Optional<User> checkUniqueFields(String email);

    @Query("select u from User u where u.username = ?1")
    User findByEmail(String username);

    Optional<User> findUserByUsername(String username);


    @Query(value = """
               select 
               u.full_name, 
               u.role,
               u.status, 
               u.username, 
               g.name as group_name, 
               f.name as faculty_name
            from users u
            left join groups g on u.group_id = g.id
            left join faculty f on g.faculty_id = f.id
            where u.full_name = :studentFullName
              """, nativeQuery = true)
    Optional<Object[][]> findUserByFullName(String studentFullName);

    @Query(value = """
                        select s.name, u.full_name, sum(m.grade)
            from users u
                     left join mark m on u.id = m.user_id
                     left join subject s on m.subject_id = s.id
            where u.group_id = :groupId
            group by s.name, u.full_name order by sum(m.grade) desc ;
                        """, nativeQuery = true)
    Optional<List<Object[]>> findStudentsAndMarksByGroupId(Integer groupId);


}