# Student Management System

A beginner-friendly Student Management System using:
- Java MVC architecture
- JDBC
- MySQL
- Node.js + Express
- HTML/CSS/JavaScript frontend
- Document export (HTML document) for a student record

## Architecture

Browser -> Node.js/Express -> Java MVC/JDBC service -> MySQL

The Node.js server launches the Java backend process and communicates with it through JSON over stdin/stdout.

## Requirements
- JDK 17+
- Node.js 18+
- MySQL 8+
- Maven 3.8+

## 1. Create the database

Run `database/student_management.sql` in MySQL.

Default database:
`student_management`

The Java connection defaults are:
- host: localhost
- port: 3306
- database: student_management
- username: root
- password: root

You can change them with environment variables:
`DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`.

## 2. Build Java backend

Open a terminal in `java-backend`:

```bash
mvn clean package
```

This creates:
`target/student-management-java-1.0.0.jar`

## 3. Start Node.js/Express

Open another terminal in `node-server`:

```bash
npm install
npm start
```

Open:
http://localhost:3000

The Node server starts the Java backend automatically.

## Features
- Add student
- View all students
- Search students by name/course
- Delete student
- Export an individual student record as an HTML document
- MySQL persistence
- Java MVC separation
- JDBC prepared statements

## Important
This is an educational project. For production, add authentication, authorization, CSRF protection, stronger validation, HTTPS, secrets management, rate limiting, audit logging, and a proper Java HTTP API such as Spring Boot or a Java Servlet deployment.
