package com.jareer.lms.app.repositories.user;

import com.jareer.lms.app.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where upper(u.username) = upper(?1)")
    Optional<User> checkUniqueFields(String email);

    @Query("select u from User u where u.username = ?1")
    User findByEmail(String username);

    Optional<User> findUserByUsername(String username);


    @Query("select u from User u")
    Optional<Collection<User>> findAllUserDetails();


}