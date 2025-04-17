package com.project.model;

public class Student extends User {

    // Конструктор
    public Student(String username, String password, String email) {
        super(username, password, email);
    }

    @Override
    public String getRole() {
        return "STUDENT";
    }

    // Студент-специфичные методы
    public void enrollInCourse(int courseId) {
        System.out.println("Enrolling in course: " + courseId);
    }

    public void startTestAttempt(int testId) {
        System.out.println("Starting test: " + testId);
    }

    public void viewTestResults(int attemptId) {
        System.out.println("Viewing results for attempt: " + attemptId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}