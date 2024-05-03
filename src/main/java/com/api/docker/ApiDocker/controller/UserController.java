package com.api.docker.ApiDocker.controller;

import com.api.docker.ApiDocker.entity.ResponseObject;
import com.api.docker.ApiDocker.entity.User;
import com.api.docker.ApiDocker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    @Autowired
    private UserRepository userrepository;

    @GetMapping(value = "/get")
    public List<User> GetAllUser() {
        return userrepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseObject> GetUserById(@PathVariable Long id) {
        Optional<User> foundUser = userrepository.findById(id);
        return foundUser.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Query user successfully", foundUser)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("failed", "Cannot find user with id = " + id, "")
                );
    }

    @PostMapping(value = "/insert")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser) {
        List<User> foundUser = userrepository.findByName(newUser.getName().trim());
        if (foundUser.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Insert user failed because name user already exists", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert user successfully", userrepository.save(newUser))
        );
    }

    @PutMapping(value = "{id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newuser, @PathVariable Long id) {
        User updateUser = userrepository.findById(id)
                .map(user -> {
                    user.setName(newuser.getName());
                    user.setSex(newuser.getSex());
                    user.setCorrect(newuser.getCorrect());
                    user.setPhoneNumber(newuser.getPhoneNumber());
                    user.setEmail(newuser.getEmail());
                    return userrepository.save(user);
                }).orElseGet(() -> {
                    newuser.setID(id);
                    return userrepository.save(newuser);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update user successfully", updateUser)
        );
    }

    @DeleteMapping(value = "{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable Long id) {
        boolean exists = userrepository.existsById(id);
        if (exists) {
            userrepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete user successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("ok", "Delete user failed", "")
        );
    }
}
