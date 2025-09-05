package com.bhlearnsphere.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "quizzes")
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;

    // Getters & Setters
    public Long getId(){ return id; }
    public String getTitle(){ return title; }
    public Course getCourse(){ return course; }
    public List<Question> getQuestions(){ return questions; }

    public void setId(Long id){ this.id = id; }
    public void setTitle(String title){ this.title = title; }
    public void setCourse(Course course){ this.course = course; }
    public void setQuestions(List<Question> questions){ this.questions = questions; }
}
