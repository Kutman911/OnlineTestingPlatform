package com.project.model;

public class Answer {
    private int id;
    private int attemptId;
    private int questionId;
    private String answerData; // JSON строка с ответом пользователя
    private Boolean isCorrect;
    private Integer pointsAwarded;

    // Конструкторы
    public Answer() {}

    public Answer(int attemptId, int questionId, String answerData) {
        this.attemptId = attemptId;
        this.questionId = questionId;
        this.answerData = answerData;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAttemptId() { return attemptId; }
    public void setAttemptId(int attemptId) { this.attemptId = attemptId; }

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public String getAnswerData() { return answerData; }
    public void setAnswerData(String answerData) { this.answerData = answerData; }

    public Boolean getIsCorrect() { return isCorrect; }
    public void setIsCorrect(Boolean isCorrect) { this.isCorrect = isCorrect; }

    public Integer getPointsAwarded() { return pointsAwarded; }
    public void setPointsAwarded(Integer pointsAwarded) { this.pointsAwarded = pointsAwarded; }
}