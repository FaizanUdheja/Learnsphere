package com.bhlearnsphere.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "enrollments")
public class Enrollment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private boolean completed;
    private Double progress;

    // Getters & Setters
    public Long getId(){ return id; }
    public User getUser(){ return user; }
    public Course getCourse(){ return course; }
    public boolean isCompleted(){ return completed; }
    public Double getProgress(){ return progress; }

    public void setId(Long id){ this.id = id; }
    public void setUser(User user){ this.user = user; }
    public void setCourse(Course course){ this.course = course; }
    public void setCompleted(boolean completed){ this.completed = completed; }
    public void setProgress(Double progress){ this.progress = progress; }
}
