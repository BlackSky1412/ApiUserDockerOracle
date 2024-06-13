package com.api.docker.ApiDocker.service;

import com.api.docker.ApiDocker.entity.User;

import java.util.List;

/**
 * IUserService interface defines the operations for managing users.
 */
public interface IUserService {

    /**
     * Retrieve all users.
     *
     * @return a list of all users
     */
    List<User> getAllUsers();

    /**
     * Retrieve a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user if found, otherwise null
     */
    User getUserById(Long id);

    /**
     * Delete a user by their ID.
     *
     * @param id the ID of the user to delete
     * @return true if the user was deleted, false if the user was not found
     */
    boolean deleteUser(Long id);

    /**
     * Update an existing user.
     *
     * @param user the new user data
     * @param id the ID of the user to be updated
     * @return true if the user was updated, false otherwise
     */

    boolean updateUser(User user, Long id);

    /**
     * Insert a new user.
     *
     * @param user the user to be inserted
     * @return true if the user was inserted, false otherwise
     */
    boolean insertUser(User user);
}
