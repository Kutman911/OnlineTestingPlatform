package com.project.model;

import java.time.LocalDateTime;
import java.util.List;

public class Test {
    private int id;
    private String title;
    private String description;
    private int timeLimitMinutes;
    private int createdBy;
    private boolean published;
    private LocalDateTime createdAt;
    private List<Question> questions;

    // Конструкторы
    public Test() {}

    public Test(String title, String description, int timeLimitMinutes, int createdBy) {
        this.title = title;
        this.description = description;
        this.timeLimitMinutes = timeLimitMinutes;
        this.createdBy = createdBy;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getTimeLimitMinutes() { return timeLimitMinutes; }
    public void setTimeLimitMinutes(int timeLimitMinutes) { this.timeLimitMinutes = timeLimitMinutes; }

    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }

    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }
}