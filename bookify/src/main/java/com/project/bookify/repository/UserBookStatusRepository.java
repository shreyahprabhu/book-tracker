package com.project.bookify.repository;

import com.project.bookify.model.Book;
import com.project.bookify.model.User;
import com.project.bookify.model.UserBookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserBookStatusRepository extends JpaRepository<UserBookStatus, Long> {
    Page<UserBookStatus> findByUser(User user, Pageable pageable);
    Page<UserBookStatus> findByBook(Book book, Pageable pageable);
    Page<UserBookStatus> findByStatusContainingIgnoreCase(String status, Pageable pageable);
}



