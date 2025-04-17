package com.project.model;

public class CourseManager extends User {
    public CourseManager(String username, String password, String email) {
        super(username, password, email);
    }

    @Override
    public String getRole() {
        return "COURSE_MANAGER";
    }

    // Менеджер-специфичные методы
    public void scheduleCourse() {
        // Логика планирования курса
    }
}