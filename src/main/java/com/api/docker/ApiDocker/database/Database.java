package com.api.docker.ApiDocker.database;

import com.api.docker.ApiDocker.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(IUserRepository userrepository)
    {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                User UserA =  new User( "Manh", 1, 1, "0979741214", "Trancongmanh.it@gmail.com", "uploads/2f8717a163e54d7181d05c83bb05eb15.jpg");
//                User UserB = new User( "Quoc", 1, 1, "0979741214", "Trancongmanh.it@gmail.com", "uploads/2f8717a163e54d7181d05c83bb05eb15.jpg");
//                logger.info("insert data: " + userrepository.save(UserA));
//                logger.info("insert data: " + userrepository.save(UserB));
            }
        };
    }
}
