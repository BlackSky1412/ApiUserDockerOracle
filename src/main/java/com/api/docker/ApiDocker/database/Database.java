package com.api.docker.ApiDocker.database;

import com.api.docker.ApiDocker.entity.User;
import com.api.docker.ApiDocker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(UserRepository userrepository)
    {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                /*User UserA =  new User( "Manh", 1, 1, "0979741214", "Trancongmanh.it@gmail.com");
                User UserB = new User( "Quoc", 1, 1, "0979741214", "Trancongmanh.it@gmail.com");
                logger.info("insert data: " + userrepository.save(UserA));
                logger.info("insert data: " + userrepository.save(UserB));*/
            }
        };
    }
}
