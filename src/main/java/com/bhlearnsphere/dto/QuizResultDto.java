package com.bhlearnsphere.dto;

public class QuizResultDto {
    private Long quizId;
    private Long userId;
    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
    private double percentage;
    private boolean passed;
    private int timeSpent;

    // Constructors
    public QuizResultDto() {}

    public QuizResultDto(Long quizId, Long userId, int totalQuestions, int correctAnswers, int incorrectAnswers, double percentage, boolean passed, int timeSpent) {
        this.quizId = quizId;
        this.userId = userId;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.percentage = percentage;
        this.passed = passed;
        this.timeSpent = timeSpent;
    }

    // Getters & Setters
    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getTotalQuestions() { return totalQuestions; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }

    public int getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(int correctAnswers) { this.correctAnswers = correctAnswers; }

    public int getIncorrectAnswers() { return incorrectAnswers; }
    public void setIncorrectAnswers(int incorrectAnswers) { this.incorrectAnswers = incorrectAnswers; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }

    public boolean isPassed() { return passed; }
    public void setPassed(boolean passed) { this.passed = passed; }

    public int getTimeSpent() { return timeSpent; }
    public void setTimeSpent(int timeSpent) { this.timeSpent = timeSpent; }
}
