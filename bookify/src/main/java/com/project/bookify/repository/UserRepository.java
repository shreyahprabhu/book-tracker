package com.project.bookify.repository;
import com.project.bookify.model.Book;
import com.project.bookify.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
/*
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
*/

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);
}