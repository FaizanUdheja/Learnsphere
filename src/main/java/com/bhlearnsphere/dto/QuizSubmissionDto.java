package com.bhlearnsphere.dto;

import java.util.Map;

public class QuizSubmissionDto {
    private Long quizId;
    private Long userId;
    private Map<Long, String> answers; // questionId -> answer
    private int timeSpent; // in seconds

    // Constructors
    public QuizSubmissionDto() {}

    public QuizSubmissionDto(Long quizId, Long userId, Map<Long, String> answers, int timeSpent) {
        this.quizId = quizId;
        this.userId = userId;
        this.answers = answers;
        this.timeSpent = timeSpent;
    }

    // Getters & Setters
    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Map<Long, String> getAnswers() { return answers; }
    public void setAnswers(Map<Long, String> answers) { this.answers = answers; }

    public int getTimeSpent() { return timeSpent; }
    public void setTimeSpent(int timeSpent) { this.timeSpent = timeSpent; }
}
