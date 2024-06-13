package com.api.docker.ApiDocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.docker.ApiDocker.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
