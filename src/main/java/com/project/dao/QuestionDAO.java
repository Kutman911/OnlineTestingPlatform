package com.project.dao;

import com.project.db.Database;
import com.project.model.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionDAO {

    // CREATE
    public boolean createQuestion(Question question) {
        String sql = "INSERT INTO questions (test_id, question_text, question_type, points, options, correct_answer) " +
                "VALUES (?, ?, ?, ?, ?::jsonb, ?::jsonb)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, question.getTestId());
            stmt.setString(2, question.getQuestionText());
            stmt.setString(3, question.getQuestionType());
            stmt.setInt(4, question.getPoints());
            stmt.setString(5, question.getOptionsJson());
            stmt.setString(6, question.getCorrectAnswerJson());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    question.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ (single)
    public Optional<Question> findById(int id) {
        String sql = "SELECT * FROM questions WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToQuestion(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // READ (all for test)
    public List<Question> findByTestId(int testId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions WHERE test_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, testId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                questions.add(mapResultSetToQuestion(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    // UPDATE
    public boolean updateQuestion(Question question) {
        String sql = "UPDATE questions SET question_text = ?, question_type = ?, points = ?, " +
                "options = ?::jsonb, correct_answer = ?::jsonb WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, question.getQuestionText());
            stmt.setString(2, question.getQuestionType());
            stmt.setInt(3, question.getPoints());
            stmt.setString(4, question.getOptionsJson());
            stmt.setString(5, question.getCorrectAnswerJson());
            stmt.setInt(6, question.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteQuestion(int id) {
        String sql = "DELETE FROM questions WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE all questions for test
    public boolean deleteQuestionsByTestId(int testId) {
        String sql = "DELETE FROM questions WHERE test_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, testId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to map ResultSet to Question object
    private Question mapResultSetToQuestion(ResultSet rs) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setTestId(rs.getInt("test_id"));
        question.setQuestionText(rs.getString("question_text"));
        question.setQuestionType(rs.getString("question_type"));
        question.setPoints(rs.getInt("points"));
        question.setOptionsJson(rs.getString("options"));
        question.setCorrectAnswerJson(rs.getString("correct_answer"));
        return question;
    }
}