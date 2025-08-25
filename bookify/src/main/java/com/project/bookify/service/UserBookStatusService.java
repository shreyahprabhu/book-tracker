package com.project.bookify.service;

import com.project.bookify.model.Book;
import com.project.bookify.model.User;
import com.project.bookify.model.UserBookStatus;
import com.project.bookify.repository.BookRepository;
import com.project.bookify.repository.UserBookStatusRepository;
import com.project.bookify.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<UserBookStatus> getAllStatuses() {
        return userBookStatusRepository.findAll();
    }

    public List<UserBookStatus> getStatusesByUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(userBookStatusRepository::findByUser).orElse(List.of());
    }

    public List<UserBookStatus> getStatusesByBook(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book.map(userBookStatusRepository::findByBook).orElse(List.of());
    }

    public UserBookStatus saveStatus(UserBookStatus status) {
        User fullUser = userRepository.findById(status.getUser().getId()).orElse(null);
        Book fullBook = bookRepository.findById(status.getBook().getId()).orElse(null);
        status.setUser(fullUser);
        status.setBook(fullBook);
        return userBookStatusRepository.save(status);
    }

    public UserBookStatus updateStatus(Long id, UserBookStatus updatedStatus) {
        UserBookStatus existingStatus = userBookStatusRepository.findById(id).orElse(null);
        if (existingStatus == null) return null;

        if (updatedStatus.getStatus() != null) existingStatus.setStatus(updatedStatus.getStatus());

        if (updatedStatus.getUser() != null) {
            User fullUser = userRepository.findById(updatedStatus.getUser().getId()).orElse(null);
            existingStatus.setUser(fullUser);
        }

        if (updatedStatus.getBook() != null) {
            Book fullBook = bookRepository.findById(updatedStatus.getBook().getId()).orElse(null);
            existingStatus.setBook(fullBook);
        }

        return userBookStatusRepository.save(existingStatus);
    }

    public void deleteStatus(Long id) {
        userBookStatusRepository.deleteById(id);
    }
}
