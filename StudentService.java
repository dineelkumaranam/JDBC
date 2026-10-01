package com.example.student.service;

import com.example.student.dao.StudentDAO;
import com.example.student.model.Student;
import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private final StudentDAO dao = new StudentDAO();

    public Student add(Student s) throws SQLException {
        if (s.getName() == null || s.getName().isBlank()) throw new SQLException("Name is required.");
        if (s.getEmail() == null || !s.getEmail().contains("@")) throw new SQLException("Valid email is required.");
        if (s.getAge() < 1 || s.getAge() > 120) throw new SQLException("Age must be between 1 and 120.");
        if (s.getCourse() == null || s.getCourse().isBlank()) throw new SQLException("Course is required.");
        return dao.add(s);
    }

    public List<Student> list() throws SQLException { return dao.findAll(); }
    public Student get(int id) throws SQLException { return dao.findById(id); }
    public void delete(int id) throws SQLException { dao.delete(id); }
}
