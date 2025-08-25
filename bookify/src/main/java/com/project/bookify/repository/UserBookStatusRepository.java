package com.project.bookify.repository;

import com.project.bookify.model.Book;
import com.project.bookify.model.User;
import com.project.bookify.model.UserBookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookStatusRepository extends JpaRepository<UserBookStatus, Long> {
    List<UserBookStatus> findByUser(User user);
    List<UserBookStatus> findByBook(Book book);
}
