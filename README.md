# JDBC + MVC + Node.js Student Management Application

This project combines:

- Node.js + Express
- HTML/CSS/JavaScript frontend
- Java MVC architecture
- JDBC
- MySQL
- Apache Tomcat
- Maven
- PreparedStatement

## Architecture

Browser
   |
   v
Node.js + Express (port 3000)
   |
   | HTTP
   v
Java Servlet MVC (Tomcat port 8080)
   |
   v
DAO -> JDBC -> MySQL

## Project structure

jdbc-mvc-nodejs-student-management/
├── java-backend/
│   ├── pom.xml
│   ├── database/schema.sql
│   ├── src/main/resources/db.properties
│   ├── src/main/java/com/example/studentmvc/model/Student.java
│   ├── src/main/java/com/example/studentmvc/util/DBConnection.java
│   ├── src/main/java/com/example/studentmvc/dao/StudentDAO.java
│   ├── src/main/java/com/example/studentmvc/controller/StudentServlet.java
│   └── src/main/webapp/WEB-INF/web.xml
└── node-frontend/
    ├── package.json
    ├── server.js
    └── public/
        ├── index.html
        ├── style.css
        └── app.js

## Requirements

1. JDK 17+
2. Maven 3.9+
3. MySQL 8+
4. Apache Tomcat 10+
5. Node.js 18+
6. npm

## Step 1: Create the database

Open MySQL and run:

database/schema.sql

Then edit:

java-backend/src/main/resources/db.properties

Set your MySQL password.

## Step 2: Build Java backend

Open a terminal in java-backend:

mvn clean package

Copy:

target/jdbc-mvc-student.war

into Tomcat's webapps folder.

Start Tomcat.

The Java backend will be available at:

http://localhost:8080/jdbc-mvc-student/students

## Step 3: Start Node.js frontend

Open another terminal in node-frontend:

npm install
npm start

The frontend will run at:

http://localhost:3000

The Node server proxies API requests to the Java MVC backend.

## Step 4: Use the application

Open:

http://localhost:3000

You can:

- Add students
- View students
- Update students
- Delete students
- Search students

## Important

Node.js does NOT directly use JDBC.

Node.js communicates with the Java Servlet through HTTP.
The Java DAO uses JDBC to communicate with MySQL.

This separation is useful for understanding a real full-stack architecture.
