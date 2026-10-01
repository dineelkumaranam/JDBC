package com.example.student.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String env(String key, String fallback) {
        String value = System.getenv(key);
        return value == null || value.isBlank() ? fallback : value;
    }

    public static Connection getConnection() throws SQLException {
        String host = env("DB_HOST", "localhost");
        String port = env("DB_PORT", "3306");
        String db = env("DB_NAME", "student_management");
        String user = env("DB_USER", "root");
        String password = env("DB_PASSWORD", "root");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + db +
                "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        return DriverManager.getConnection(url, user, password);
    }
}
