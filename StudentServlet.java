package com.example.studentmvc.controller;

import com.example.studentmvc.dao.StudentDAO;
import com.example.studentmvc.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private final StudentDAO dao = new StudentDAO();

    private void sendJson(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private String esc(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }

    private String studentsToJson(List<Student> students) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (i > 0) json.append(",");
            json.append("{")
                .append("\"id\":").append(s.getId()).append(",")
                .append("\"name\":\"").append(esc(s.getName())).append("\",")
                .append("\"age\":").append(s.getAge()).append(",")
                .append("\"course\":\"").append(esc(s.getCourse())).append("\"")
                .append("}");
        }
        return json.append("]").toString();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        try {
            sendJson(response, studentsToJson(dao.findAll()));
        } catch (Exception e) {
            response.setStatus(500);
            sendJson(response, "{\"error\":\"" + esc(e.getMessage()) + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        try {
            String action = request.getParameter("action");

            if ("delete".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.delete(id);
            } else if ("update".equalsIgnoreCase(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                int age = Integer.parseInt(request.getParameter("age"));
                String course = request.getParameter("course");
                dao.update(new Student(id, name, age, course));
            } else {
                String name = request.getParameter("name");
                int age = Integer.parseInt(request.getParameter("age"));
                String course = request.getParameter("course");
                dao.save(new Student(name, age, course));
            }

            sendJson(response, "{\"success\":true}");
        } catch (Exception e) {
            response.setStatus(500);
            sendJson(response, "{\"error\":\"" + esc(e.getMessage()) + "\"}");
        }
    }
}
