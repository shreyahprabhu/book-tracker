package com.project.bookify.service;

import com.project.bookify.model.Book;
import com.project.bookify.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.project.bookify.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }
    /*
    public List<Book> searchByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> searchByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
     */

    public Page<Book> getBooksByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Page<Book> getBooksByAuthor(String author, Pageable pageable) {
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setGenre(updatedBook.getGenre());
        existingBook.setYearPublished(updatedBook.getYearPublished());
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(existingBook);
    }
}

