package com.project.bookify.controller;

import com.project.bookify.model.UserBookStatus;
import com.project.bookify.service.UserBookStatusService;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/user-book-status")
public class UserBookStatusController {

    private final UserBookStatusService userBookStatusService;

    public UserBookStatusController(UserBookStatusService userBookStatusService) {
        this.userBookStatusService = userBookStatusService;
    }

    @GetMapping
    public Page<UserBookStatus> getAllStatuses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userBookStatusService.getAllStatuses(pageable);
    }

    @GetMapping("/user/{userId}")
    public Page<UserBookStatus> getStatusesByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userBookStatusService.getStatusesByUser(userId, pageable);
    }

    @GetMapping("/book/{bookId}")
    public Page<UserBookStatus> getStatusesByBook(
            @PathVariable Long bookId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userBookStatusService.getStatusesByBook(bookId, pageable);
    }

    @GetMapping("/status")
    public Page<UserBookStatus> getStatusesByStatus(
            @RequestParam String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userBookStatusService.getStatusesByStatus(status, pageable);
    }

    @PostMapping
    public UserBookStatus addStatus(@RequestBody UserBookStatus status) {
        return userBookStatusService.saveStatus(status);
    }

    @PutMapping("/{id}")
    public UserBookStatus updateStatus(@PathVariable Long id, @RequestBody UserBookStatus updatedStatus) {
        return userBookStatusService.updateStatus(id, updatedStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteStatus(@PathVariable Long id) {
        userBookStatusService.deleteStatus(id);
    }
}
