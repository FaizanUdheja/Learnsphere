package com.bhlearnsphere.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String text;
    private String type;
    private String options; // store as CSV or JSON
    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    // Getters & Setters
    public Long getId(){ return id; }
    public String getText(){ return text; }
    public String getType(){ return type; }
    public String getOptions(){ return options; }
    public String getCorrectAnswer(){ return correctAnswer; }
    public Quiz getQuiz(){ return quiz; }

    public void setId(Long id){ this.id = id; }
    public void setText(String text){ this.text = text; }
    public void setType(String type){ this.type = type; }
    public void setOptions(String options){ this.options = options; }
    public void setCorrectAnswer(String correctAnswer){ this.correctAnswer = correctAnswer; }
    public void setQuiz(Quiz quiz){ this.quiz = quiz; }
}
