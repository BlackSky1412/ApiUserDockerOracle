package com.api.docker.ApiDocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.docker.ApiDocker.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String Name);
}
