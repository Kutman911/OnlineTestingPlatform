package com.project.dao;

import com.project.db.Database;
import com.project.model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDAO {

    // CREATE
    public boolean createCourse(Course course) {
        String sql = "INSERT INTO courses (title, description, created_by) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, course.getTitle());
            stmt.setString(2, course.getDescription());
            stmt.setInt(3, course.getCreatedBy());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                return false;
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    course.setId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ (single)
    public Optional<Course> findById(int id) {
        String sql = "SELECT * FROM courses WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // READ (all)
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // READ (by creator)
    public List<Course> findByCreator(int createdBy) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE created_by = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, createdBy);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                courses.add(mapResultSetToCourse(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    // UPDATE
    public boolean updateCourse(Course course) {
        String sql = "UPDATE courses SET title = ?, description = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getTitle());
            stmt.setString(2, course.getDescription());
            stmt.setInt(3, course.getId());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteCourse(int id) {
        String sql = "DELETE FROM courses WHERE id = ?";

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

    // Helper method to map ResultSet to Course object
    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setTitle(rs.getString("title"));
        course.setDescription(rs.getString("description"));
        course.setCreatedBy(rs.getInt("created_by"));
        course.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return course;
    }
}