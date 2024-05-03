package com.api.docker.ApiDocker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(
            name  = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long ID;
    @Column(nullable = false, length = 50)
    private String name;
    private int sex;
    private int correct;
    @Column(nullable = true, length = 11)
    private String phoneNumber;
    @Column(nullable = true, length = 50)
    private String email;
    private String url;

    public User( String name, int sex, int correct, String phoneNumber, String email, String url) {
        this.name = name;
        this.sex = sex;
        this.correct = correct;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.url = url;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", correct=" + correct +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
