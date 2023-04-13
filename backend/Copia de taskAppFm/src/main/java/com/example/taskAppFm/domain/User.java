package com.example.taskAppFm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private Integer score;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Task> tasks = new HashSet<>();


    public User() {
    }

    public User(String name, String password, Integer score) {
        this.name = name;
        this.password = password;
        this.score = score;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }



}
