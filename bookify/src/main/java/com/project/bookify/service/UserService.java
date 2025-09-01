package com.project.bookify.service;

import com.project.bookify.model.User;
import com.project.bookify.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.project.bookify.exception.ResourceNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
//import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
       return userRepository.findById(id).orElse(null);
    }
    */

    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /*
    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User existingUser = optionalUser.get();
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    */

    public Page<User> getUsersByUsername(String username, Pageable pageable) {
        return userRepository.findByUsernameContainingIgnoreCase(username, pageable);
    }

    public Page<User> getUsersByEmail(String email, Pageable pageable) {
        return userRepository.findByEmailContainingIgnoreCase(email, pageable);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(existingUser);
    }

}

