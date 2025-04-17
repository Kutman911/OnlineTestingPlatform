package com.project.dao;

import com.project.db.Database;
import com.project.model.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestDAO {

    // CREATE
    public boolean createTest(Test test) {
        String sql = "INSERT INTO tests (title, description, time_limit_minutes, created_by, is_published) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, test.getTitle());
            stmt.setString(2, test.getDescription());
            stmt.setInt(3, test.getTimeLimitMinutes());
            stmt.setInt(4, test.getCreatedBy());
            stmt.setBoolean(5, test.isPublished());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    test.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ (single)
    public Optional<Test> findById(int id) {
        String sql = "SELECT * FROM tests WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToTest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // READ (all)
    public List<Test> findAll() {
        List<Test> tests = new ArrayList<>();
        String sql = "SELECT * FROM tests";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tests.add(mapResultSetToTest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    // READ (by creator)
    public List<Test> findByCreator(int createdBy) {
        List<Test> tests = new ArrayList<>();
        String sql = "SELECT * FROM tests WHERE created_by = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, createdBy);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tests.add(mapResultSetToTest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    // READ (published only)
    public List<Test> findPublishedTests() {
        List<Test> tests = new ArrayList<>();
        String sql = "SELECT * FROM tests WHERE is_published = true";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                tests.add(mapResultSetToTest(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    // UPDATE
    public boolean updateTest(Test test) {
        String sql = "UPDATE tests SET title = ?, description = ?, time_limit_minutes = ?, " +
                "is_published = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, test.getTitle());
            stmt.setString(2, test.getDescription());
            stmt.setInt(3, test.getTimeLimitMinutes());
            stmt.setBoolean(4, test.isPublished());
            stmt.setInt(5, test.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteTest(int id) {
        String sql = "DELETE FROM tests WHERE id = ?";

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

    // Helper method to map ResultSet to Test object
    private Test mapResultSetToTest(ResultSet rs) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt("id"));
        test.setTitle(rs.getString("title"));
        test.setDescription(rs.getString("description"));
        test.setTimeLimitMinutes(rs.getInt("time_limit_minutes"));
        test.setCreatedBy(rs.getInt("created_by"));
        test.setPublished(rs.getBoolean("is_published"));
        test.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return test;
    }
}