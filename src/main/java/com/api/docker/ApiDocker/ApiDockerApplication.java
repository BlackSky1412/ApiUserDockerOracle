package com.api.docker.ApiDocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.api.docker.ApiDocker.repository")
@EntityScan(basePackages = "com.api.docker.ApiDocker.entity")
public class ApiDockerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiDockerApplication.class, args);
	}
}

