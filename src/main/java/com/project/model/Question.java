package com.project.model;

public class Question {
    private int id;
    private int testId;
    private String questionText;
    private String questionType; // SINGLE_CHOICE, MULTIPLE_CHOICE, TEXT_ANSWER
    private int points;
    private String optionsJson; // JSON строка с вариантами ответов
    private String correctAnswerJson; // JSON строка с правильными ответами

    // Конструкторы
    public Question() {}

    public Question(int testId, String questionText, String questionType) {
        this.testId = testId;
        this.questionText = questionText;
        this.questionType = questionType;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTestId() { return testId; }
    public void setTestId(int testId) { this.testId = testId; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getQuestionType() { return questionType; }
    public void setQuestionType(String questionType) { this.questionType = questionType; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getOptionsJson() { return optionsJson; }
    public void setOptionsJson(String optionsJson) { this.optionsJson = optionsJson; }

    public String getCorrectAnswerJson() { return correctAnswerJson; }
    public void setCorrectAnswerJson(String correctAnswerJson) { this.correctAnswerJson = correctAnswerJson; }
}