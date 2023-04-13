package com.example.taskAppFm.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer points;
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private TaskState taskState;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Task() {
    }

    public Task(String name, Integer points, LocalDateTime dateTime, TaskState taskState, User user) {
        this.name = name;
        this.points = points;
        this.dateTime = dateTime;
        this.taskState = taskState;
        this.user = user;
    }

    public Task(String name, Integer points, LocalDateTime dateTime) {
        this.name = name;
        this.points = points;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
