package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class StudentController {
    private final ObjectMapper mapper = new ObjectMapper();
    private final StudentService service = new StudentService();

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                ObjectNode response = mapper.createObjectNode();
                try {
                    JsonNode request = mapper.readTree(line);
                    response.put("requestId", request.get("requestId").asInt());
                    String action = request.get("action").asText();

                    switch (action) {
                        case "list" -> {
                            List<Student> students = service.list();
                            response.put("ok", true);
                            response.set("students", mapper.valueToTree(students));
                        }
                        case "add" -> {
                            Student s = mapper.treeToValue(request.get("student"), Student.class);
                            Student saved = service.add(s);
                            response.put("ok", true);
                            response.set("student", mapper.valueToTree(saved));
                        }
                        case "get" -> {
                            Student s = service.get(request.get("id").asInt());
                            response.put("ok", true);
                            response.set("student", mapper.valueToTree(s));
                        }
                        case "delete" -> {
                            service.delete(request.get("id").asInt());
                            response.put("ok", true);
                        }
                        default -> throw new IllegalArgumentException("Unknown action: " + action);
                    }
                } catch (Exception e) {
                    response.put("ok", false);
                    response.put("error", e.getMessage() == null ? "Server error" : e.getMessage());
                }
                System.out.println(mapper.writeValueAsString(response));
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println("Java controller stopped: " + e.getMessage());
        }
    }
}
