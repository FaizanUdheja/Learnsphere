package com.bhlearnsphere.dto;

import java.util.List;

public class QuestionDto {
    private Long id;
    private String text;
    private String type;
    private List<String> options;
    private String correctAnswer;
    private Long quizId;

    // Constructors
    public QuestionDto() {}

    public QuestionDto(Long id, String text, String type, List<String> options, String correctAnswer, Long quizId) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.quizId = quizId;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }
}
