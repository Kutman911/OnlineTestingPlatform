package com.project.model;

import java.time.LocalDateTime;

public class CourseEnrollment {
    private int id;
    private int courseId;
    private int userId;
    private LocalDateTime enrolledAt;

    // Конструкторы
    public CourseEnrollment() {}

    public CourseEnrollment(int courseId, int userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public LocalDateTime getEnrolledAt() { return enrolledAt; }
    public void setEnrolledAt(LocalDateTime enrolledAt) { this.enrolledAt = enrolledAt; }
}