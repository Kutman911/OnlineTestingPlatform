package com.project.model;

public class TestResult {
    private int testId;
    private String testTitle;
    private int score;
    private int maxScore;
    private double percentage;
    private String status;

    // Конструкторы
    public TestResult() {}

    public TestResult(int testId, String testTitle, int score, int maxScore) {
        this.testId = testId;
        this.testTitle = testTitle;
        this.score = score;
        this.maxScore = maxScore;
        this.percentage = (double) score / maxScore * 100;
        this.status = percentage >= 60 ? "PASSED" : "FAILED";
    }

    // Геттеры и сеттеры
    public int getTestId() { return testId; }
    public void setTestId(int testId) { this.testId = testId; }

    public String getTestTitle() { return testTitle; }
    public void setTestTitle(String testTitle) { this.testTitle = testTitle; }

    public int getScore() { return score; }
    public void setScore(int score) {
        this.score = score;
        this.percentage = (double) score / maxScore * 100;
        this.status = percentage >= 60 ? "PASSED" : "FAILED";
    }

    public int getMaxScore() { return maxScore; }
    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
        this.percentage = (double) score / maxScore * 100;
        this.status = percentage >= 60 ? "PASSED" : "FAILED";
    }

    public double getPercentage() { return percentage; }

    public String getStatus() { return status; }
}