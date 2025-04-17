package com.project.model;

import java.time.LocalDateTime;
import java.util.List;

public class TestAttempt {
    private int id;
    private int testId;
    private int userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer score;
    private String status; // IN_PROGRESS, COMPLETED, GRADED
    private List<Answer> answers;

    // Конструкторы
    public TestAttempt() {}

    public TestAttempt(int testId, int userId) {
        this.testId = testId;
        this.userId = userId;
        this.status = "IN_PROGRESS";
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTestId() { return testId; }
    public void setTestId(int testId) { this.testId = testId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }
}