package com.project.bookify.service;

import com.project.bookify.model.Book;
import com.project.bookify.model.User;
import com.project.bookify.model.UserBookStatus;
import com.project.bookify.repository.BookRepository;
import com.project.bookify.repository.UserBookStatusRepository;
import com.project.bookify.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.project.bookify.exception.ResourceNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserBookStatusService {

    private final UserBookStatusRepository userBookStatusRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserBookStatusService(UserBookStatusRepository userBookStatusRepository,
                                 UserRepository userRepository,
                                 BookRepository bookRepository) {
        this.userBookStatusRepository = userBookStatusRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Page<UserBookStatus> getAllStatuses(Pageable pageable) {
        return userBookStatusRepository.findAll(pageable);
    }

    public Page<UserBookStatus> getStatusesByUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return userBookStatusRepository.findByUser(user, pageable);
    }

    public Page<UserBookStatus> getStatusesByBook(Long bookId, Pageable pageable) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        return userBookStatusRepository.findByBook(book, pageable);
    }

    public Page<UserBookStatus> getStatusesByStatus(String status, Pageable pageable) {
        return userBookStatusRepository.findByStatusContainingIgnoreCase(status, pageable);
    }

    public UserBookStatus saveStatus(UserBookStatus status) {
        User user = userRepository.findById(status.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + status.getUser().getId()));
        Book book = bookRepository.findById(status.getBook().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + status.getBook().getId()));

        status.setUser(user);
        status.setBook(book);
        return userBookStatusRepository.save(status);
    }

    public UserBookStatus updateStatus(Long id, UserBookStatus updatedStatus) {
        UserBookStatus existingStatus = userBookStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found with id: " + id));

        if (updatedStatus.getStatus() != null) existingStatus.setStatus(updatedStatus.getStatus());

        if (updatedStatus.getUser() != null) {
            User user = userRepository.findById(updatedStatus.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updatedStatus.getUser().getId()));
            existingStatus.setUser(user);
        }

        if (updatedStatus.getBook() != null) {
            Book book = bookRepository.findById(updatedStatus.getBook().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + updatedStatus.getBook().getId()));
            existingStatus.setBook(book);
        }

        return userBookStatusRepository.save(existingStatus);
    }

    public void deleteStatus(Long id) {
        UserBookStatus existingStatus = userBookStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Status not found with id: " + id));
        userBookStatusRepository.delete(existingStatus);
    }
}
