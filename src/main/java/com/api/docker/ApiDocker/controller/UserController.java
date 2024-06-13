package com.api.docker.ApiDocker.controller;

import com.api.docker.ApiDocker.entity.ResponseObject;
import com.api.docker.ApiDocker.entity.User;
import com.api.docker.ApiDocker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * UserController class handles HTTP requests related to User operations.
 */
@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController, autowires UserService.
     *
     * @param userService the service handling user operations
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users.
     *
     * @return ResponseEntity containing the response object with all users
     */
    @GetMapping(value = "/get")
    public ResponseEntity<ResponseObject> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query users successfully", userService.getAllUsers())
        );
    }

    /**
     * Get a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return ResponseEntity containing the response object with the user data or an error message
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Long id) {
        Optional<User> foundUser = Optional.ofNullable(userService.getUserById(id));

        return foundUser.map(user -> ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query user successfully", user)
        )).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find user with id = " + id, null)
        ));
    }

    /**
     * Insert a new user.
     *
     * @param newUser the user to be inserted
     * @return ResponseEntity containing the response object with success or failure message
     */
    @PostMapping(value = "/insert")
    public ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser) {
        if (!userService.insertUser(newUser)) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Insert user failed because username already exists", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert user successfully", "")
        );
    }

    /**
     * Update an existing user.
     *
     * @param newUser the new user data
     * @param id the ID of the user to be updated
     * @return ResponseEntity containing the response object with success or failure message
     */
    @PutMapping(value = "{id}")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        if (!userService.updateUser(newUser, id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Update user failed", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update user successfully", "")
        );
    }

    /**
     * Delete a user by their ID.
     *
     * @param id the ID of the user to be deleted
     * @return ResponseEntity containing the response object with success or failure message
     */
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete user successfully", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Delete user failed", "")
            );
        }
    }
}
