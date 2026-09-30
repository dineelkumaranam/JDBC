package com.studentmanagement.config;
public final class DatabaseConfig {
 private DatabaseConfig(){}
 public static final String URL="jdbc:mysql://localhost:3306/student_management?useSSL=false&serverTimezone=UTC";
 public static final String USER="root";
 public static final String PASSWORD="root"; // change this
 public static final int PORT=8080;
}
