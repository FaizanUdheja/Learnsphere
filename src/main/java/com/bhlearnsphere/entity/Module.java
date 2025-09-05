package com.bhlearnsphere.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "modules")
public class Module implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "module")
    private List<Lesson> lessons;

    // Getters & Setters
    public Long getId(){ return id; }
    public String getTitle(){ return title; }
    public Course getCourse(){ return course; }
    public List<Lesson> getLessons(){ return lessons; }

    public void setId(Long id){ this.id = id; }
    public void setTitle(String title){ this.title = title; }
    public void setCourse(Course course){ this.course = course; }
    public void setLessons(List<Lesson> lessons){ this.lessons = lessons; }
}
