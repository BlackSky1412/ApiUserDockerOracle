package com.api.docker.ApiDocker.service;

import com.api.docker.ApiDocker.entity.User;
import com.api.docker.ApiDocker.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 */
@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    /**
     * Constructor for UserService, autowires IUserRepository.
     *
     * @param userRepository the repository handling user data
     */
    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get all users.
     *
     * @return list of all users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user if found, otherwise null
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Delete a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return true if the user was deleted, false if the user was not found
     */
    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update an existing user.
     *
     * @param newUser the new user data
     * @param id the ID of the user to be updated
     * @return true if the user was updated, false otherwise
     */
    @Override
    public boolean updateUser(User newUser, Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(newUser.getName());
            user.setSex(newUser.getSex());
            user.setCorrect(newUser.getCorrect());
            user.setPhoneNumber(newUser.getPhoneNumber());
            user.setEmail(newUser.getEmail());
            userRepository.save(user);
        } else {
            userRepository.save(newUser);
        }
        return true;
    }

    /**
     * Insert a new user.
     *
     * @param user the user to be inserted
     * @return true if the user was inserted, false otherwise
     */
    @Override
    public boolean insertUser(User user) {
        if (user == null) {
            return false; // or throw an IllegalArgumentException if you prefer
        }
        userRepository.save(user);
        return true;
    }
}
