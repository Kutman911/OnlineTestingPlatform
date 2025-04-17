package com.project.model;

import java.time.LocalDateTime;
import java.util.List;

public class Course {
    private int id;
    private String title;
    private String description;
    private int createdBy;
    private LocalDateTime createdAt;
    private List<Test> tests;

    // Конструкторы
    public Course() {}

    public Course(String title, String description, int createdBy) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<Test> getTests() { return tests; }
    public void setTests(List<Test> tests) { this.tests = tests; }
}