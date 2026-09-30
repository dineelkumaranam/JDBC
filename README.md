# Student Management System

Architecture: Browser -> Node.js/Express (3000) -> Java MVC/JDBC API (8080) -> MySQL.

Requirements: Node.js 18+, Java 17+, MySQL 8+, MySQL Connector/J.

1. Run `database/student_management.sql` in MySQL.
2. Put MySQL Connector/J JAR at `backend-java/lib/mysql-connector-j.jar`.
3. Edit DB credentials in `backend-java/src/main/java/com/studentmanagement/config/DatabaseConfig.java`.
4. Run `backend-java/build.sh` (Linux/macOS) or `build.bat` (Windows).
5. In another terminal: `cd node-express && npm install && npm start`.
6. Open http://localhost:3000

CRUD API: GET/POST /api/students, GET/PUT/DELETE /api/students/:id.
