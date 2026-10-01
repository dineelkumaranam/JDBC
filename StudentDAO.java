package com.example.student.dao;

import com.example.student.config.DatabaseConnection;
import com.example.student.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public Student add(Student s) throws SQLException {
        String sql = "INSERT INTO students(name,email,age,course,phone) VALUES(?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getCourse());
            ps.setString(5, s.getPhone());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) s.setId(rs.getInt(1));
            }
            return findById(s.getId());
        }
    }

    public List<Student> findAll() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id DESC";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    public Student findById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        throw new SQLException("Student not found.");
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) throw new SQLException("Student not found.");
        }
    }

    private Student map(ResultSet rs) throws SQLException {
        Timestamp ts = rs.getTimestamp("created_at");
        return new Student(
            rs.getInt("id"), rs.getString("name"), rs.getString("email"),
            rs.getInt("age"), rs.getString("course"), rs.getString("phone"),
            ts == null ? "" : ts.toString()
        );
    }
}
