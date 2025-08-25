package com.project.bookify.controller;

import com.project.bookify.model.UserBookStatus;
import com.project.bookify.service.UserBookStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-book-status")
public class UserBookStatusController {

    private final UserBookStatusService userBookStatusService;


    public UserBookStatusController(UserBookStatusService userBookStatusService) {
        this.userBookStatusService = userBookStatusService;
    }

    @GetMapping
    public List<UserBookStatus> getAllStatuses() {
        return userBookStatusService.getAllStatuses();
    }

    @GetMapping("/user/{userId}")
    public List<UserBookStatus> getStatusesByUser(@PathVariable Long userId) {
        return userBookStatusService.getStatusesByUser(userId);
    }

    @GetMapping("/book/{bookId}")
    public List<UserBookStatus> getStatusesByBook(@PathVariable Long bookId) {
        return userBookStatusService.getStatusesByBook(bookId);
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
