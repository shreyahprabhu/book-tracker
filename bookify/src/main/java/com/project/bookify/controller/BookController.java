package com.project.bookify.controller;

import com.project.bookify.model.Book;
import com.project.bookify.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /*
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }


    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookService.getAllBooks(pageable);
    }
    */

    @GetMapping
    public Page<Book> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        if (title != null) {
            return bookService.getBooksByTitle(title, pageable);
        } else if (author != null) {
            return bookService.getBooksByAuthor(author, pageable);
        } else {
            return bookService.getAllBooks(pageable);
        }
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    /*
    @GetMapping("/search/title/{title}")
    public List<Book> searchBooksByTitle(@PathVariable String title) {
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/search/author/{author}")
    public List<Book> searchBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }
    */

    @GetMapping("/search/title/{title}")
    public Page<Book> getBooksByTitle(
            @PathVariable String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookService.getBooksByTitle(title, pageable);
    }

    @GetMapping("/search/author/{author}")
    public Page<Book> getBooksByAuthor(
            @PathVariable String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookService.getBooksByAuthor(author, pageable);
    }


    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
